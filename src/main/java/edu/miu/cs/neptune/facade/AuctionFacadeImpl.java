package edu.miu.cs.neptune.facade;

import edu.miu.cs.neptune.domain.Auction;
import edu.miu.cs.neptune.domain.Bid;
import edu.miu.cs.neptune.domain.User;
import edu.miu.cs.neptune.service.AuctionService;
import edu.miu.cs.neptune.service.BiddingService;
import edu.miu.cs.neptune.service.ShippingService;
import edu.miu.cs.neptune.service.UserService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuctionFacadeImpl implements AuctionFacade {

    private final UserService userService;
    private final BiddingService biddingService;
    private final AuctionService auctionService;
    private final ShippingService shippingService;

    public AuctionFacadeImpl(UserService userService, BiddingService biddingService, AuctionService auctionService, ShippingService shippingService) {
        this.userService = userService;
        this.biddingService = biddingService;
        this.auctionService = auctionService;
        this.shippingService = shippingService;
    }

    @Override
    public Bid createBid(Bid bid, User user) {
        return null;
    }

    @Override
    public List<Bid> getUserBids(User user) {
        return null;
    }

    @Override
    public Auction closeAuction(Auction auction) {
        return null;
    }

    @Override
    public boolean returnDeposit(Auction auction) {
        return false;
    }

    @Override
    public User winner(Auction auction) {
        return null;
    }
}
