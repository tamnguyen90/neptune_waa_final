package edu.miu.cs.neptune.facade;

import edu.miu.cs.neptune.domain.*;
import edu.miu.cs.neptune.exception.BiddingAmountException;
import edu.miu.cs.neptune.exception.ObjectNotFoundException;
import edu.miu.cs.neptune.exception.OvertimeBiddingException;
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

    public AuctionFacadeImpl(UserService userService, BiddingService biddingService, AuctionService auctionService, ShippingService shippingService, SystemPaymentService systemPaymentService, MailService mailService) {
        this.userService = userService;
        this.biddingService = biddingService;
        this.auctionService = auctionService;
        this.shippingService = shippingService;
        this.systemPaymentService = systemPaymentService;
        this.mailService = mailService;
    }

    @Override
    public Bid createBid(Bid bid, User user, Long auctionId) {
        Optional<Auction> optAuction = auctionService.getById(auctionId);
        if (!optAuction.isPresent()) {
            throw new ObjectNotFoundException("Auction is not found.");
        }
        Auction currentAuction = optAuction.get();
        if (bid.getBiddingTime().isAfter(currentAuction.getEndDate()) || !AuctionStatus.ACTIVE.equals(currentAuction.getAuctionStatus())) {
            throw new OvertimeBiddingException("The bidding time is ended.");
        }

        Optional<Bid> currentHighestBid = biddingService.getHighestBidByAuctionId(currentAuction.getAuctionId());
        if (!currentHighestBid.isPresent() && bid.getBiddingAmount() < currentAuction.getBeginPrice()) {
            throw new BiddingAmountException("The bidding amount must be greater than the initial price.");
        } else if (currentHighestBid.get().getBiddingAmount() >= bid.getBiddingAmount()) {
            throw new BiddingAmountException("The bidding amount must be greater than the previous one.");
        }
        Bid savedBid = biddingService.save(bid);
        user.addBid(savedBid);
        currentAuction.addBid(bid);
        userService.saveUser(user);
        auctionService.save(currentAuction);
        return savedBid;
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
            throw new ObjectNotFoundException("The auction is not found.");
        }
        Auction currentAuction = optAuction.get();
        if (currentAuction.getEndDate().isBefore(LocalDateTime.now())) {
            currentAuction.setAuctionStatus(AuctionStatus.ENDED);
            if (currentAuction.getBids().size() > 0) {
                Bid highest = currentAuction.getBids()
                        .stream()
                        .max((bid1, bid2) -> bid1.getBiddingAmount().compareTo(bid2.getBiddingAmount()))
                        .get();
                currentAuction.setWinnerId(highest.getBidder().getUserId());
                //TODO Send a notification email to seller and winner
            }
        }
        return currentAuction;
    }

    @Override
    public boolean returnDeposit(Long auctionId) {
        Optional<Auction> optAuction = auctionService.getById(auctionId);
        if (!optAuction.isPresent()) {
            throw new ObjectNotFoundException("The auction is not found.");
        }
        Auction currentAuction = optAuction.get();
        if (!currentAuction.getAuctionStatus().equals(AuctionStatus.ENDED)) {
            throw new OvertimeBiddingException("The auction isn't finished yet.");
        }
        List<SystemPayment> systemPayments = systemPaymentService.getPaymentsByAuction(currentAuction.getAuctionId());
        systemPayments.forEach(payment -> {
            if (!payment.getUserId().equals(currentAuction.getWinnerId())) {
                //TODO need review the payment structure and logic
            }
        });
        return false;
    }

    @Override
    public User winner(Long auctionId) {
        Optional<Auction> optAuction = auctionService.getById(auctionId);
        if (!optAuction.isPresent()) {
            throw new ObjectNotFoundException("The auction is not found.");
        }
        Long winnerId = optAuction.get().getWinnerId();
        return userService.getById(winnerId).orElse(null);
    }
}
