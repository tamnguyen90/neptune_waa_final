package edu.miu.cs.neptune.facade;

import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import edu.miu.cs.neptune.domain.*;
import edu.miu.cs.neptune.service.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AuctionFacadeImpl implements AuctionFacade {

    private final UserService userService;
    private final BiddingService biddingService;
    private final AuctionService auctionService;
    private final ShippingService shippingService;
    private final SystemPaymentService systemPaymentService;
    private final MailService mailService;
    private final PaypalService paypalService;

    public AuctionFacadeImpl(UserService userService, BiddingService biddingService, AuctionService auctionService, ShippingService shippingService, SystemPaymentService systemPaymentService, MailService mailService, PaypalService paypalService) {
        this.userService = userService;
        this.biddingService = biddingService;
        this.auctionService = auctionService;
        this.shippingService = shippingService;
        this.systemPaymentService = systemPaymentService;
        this.mailService = mailService;
        this.paypalService = paypalService;
    }

    @Override
    public Bid createBid(Bid bid, User user, Auction currentAuction) {

        if (bid.getBiddingTime().isAfter(currentAuction.getEndDate()) || !AuctionStatus.ACTIVE.equals(currentAuction.getAuctionStatus())) {
            throw new RuntimeException("The bidding time is ended.");
        }

        Bid currentHighestBid = biddingService.getHighestBidByAuctionId(currentAuction.getAuctionId()).orElse(null);
        if (currentHighestBid == null && bid.getBiddingAmount() < currentAuction.getBeginPrice()) {
            throw new RuntimeException("The bidding amount must be greater than the initial price.");
        } else if (currentHighestBid!= null && currentHighestBid.getBiddingAmount() >= bid.getBiddingAmount()) {
            throw new RuntimeException("The bidding amount must be greater than the previous one.");
        }
        //Bid savedBid = biddingService.save(bid);
        bid.setBidder(user);
        bid.setAuction(currentAuction);
        user.addBid(bid);
        currentAuction.addBid(bid);

        return  biddingService.save(bid);
    }

    @Override
    public List<Bid> getUserBidsByAuction(Long auctionId, User user) {
        List<Bid> userBids = user.getBids();
        return userBids.stream()
                .filter(bid -> auctionId.equals(bid.getAuction().getAuctionId()))
                .collect(Collectors.toList());
    }

    @Override
    public Auction closeAuction(Long auctionId) {
        Optional<Auction> optAuction = auctionService.getById(auctionId);
        if (!optAuction.isPresent()) {
            throw new RuntimeException("The auction is not found.");
        }
        Auction currentAuction = optAuction.get();
        if (currentAuction.getEndDate().isBefore(LocalDateTime.now())) {
            currentAuction.setAuctionStatus(AuctionStatus.ENDED);
            if (currentAuction.getBids().size() > 0) {
                Bid highest = getTheHighestBid(currentAuction);
                currentAuction.setWinnerId(highest.getBidder().getUserId());
                //Send a notification email to seller and winner
                sendMailToWinner(highest.getBidder());

            }
        }
        auctionService.save(currentAuction);
        return currentAuction;
    }

    private void sendMailToWinner(User bidder) {
        //TODO send mail to the winner
    }

    @Override
    public boolean returnDeposit(Long auctionId) {
        Optional<Auction> optAuction = auctionService.getById(auctionId);
        if (!optAuction.isPresent()) {
            throw new RuntimeException("The auction is not found.");
        }
        Auction currentAuction = optAuction.get();
        if (!currentAuction.getAuctionStatus().equals(AuctionStatus.ENDED)) {
            throw new RuntimeException("The auction isn't finished yet.");
        }
        List<SystemPayment> systemPayments = systemPaymentService.getPaymentsByAuction(currentAuction.getAuctionId());
        systemPayments.forEach(payment -> {
            if (!payment.getUserId().equals(currentAuction.getWinnerId())) {
                //paypalService.refundPayment();
            }
        });
        return false;
    }

    @Override
    public User winner(Long auctionId) {
        Optional<Auction> optAuction = auctionService.getById(auctionId);
        if (!optAuction.isPresent()) {
            throw new RuntimeException("The auction is not found.");
        }
        Long winnerId = optAuction.get().getWinnerId();
        return userService.getById(winnerId).orElse(null);
    }

    @Override
    public Bid getTheHighestBid(Auction auction) {
        if (auction == null) {
            throw new RuntimeException("The auction is not found.");
        }

        Bid highest = null;
        if (auction.getBids().size() > 0) {
            highest = auction.getBids()
                    .stream()
                    .max((bid1, bid2) -> bid1.getBiddingAmount().compareTo(bid2.getBiddingAmount()))
                    .get();

        }
        return highest;
    }

    @Override
    public List<Auction> getALlActiveAuctions() {
        return auctionService.getAllActiveAuctions();
    }

    @Override
    public Auction getAuctionById(Long auctionId) {
       return auctionService.getById(auctionId).orElse(null);
    }

    @Override
    public List<Auction> getAllAuctionsByUserId(long userId) {
        return auctionService.getAllByUserId(userId);
    }

    @Override
    public AuctionOrder getAuctionOrderByAuctionId(long auctionId) {
        return paypalService.getAuctionOrder(auctionId);
    }

    @Override
    public Payment createPayment(Double price, String currency, String method, String intent, String description, String s, String s1) throws PayPalRESTException {
        return paypalService.createPayment(price, currency, method, intent, description, s, s1);
    }

    @Override
    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
        return paypalService.executePayment(paymentId, payerId);
    }

    @Override
    public Auction saveAuction(Auction auction) {
        return auctionService.save(auction);
    }

    @Override
    public User getUserByUserName(String name) {
        return userService.getByName(name).orElse(null);
    }

}
