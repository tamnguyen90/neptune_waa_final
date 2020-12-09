package edu.miu.cs.neptune.facade;

import edu.miu.cs.neptune.domain.Auction;
import edu.miu.cs.neptune.domain.Bid;
import edu.miu.cs.neptune.domain.User;

import java.util.List;

public interface AuctionFacade {
    Bid createBid(Bid bid, User user, Long auctionId);
    List<Bid> getUserBidsByAuction(Long auctionId, User user);
    Auction closeAuction(Long auctionId);
    boolean returnDeposit(Long auctionId);
    User winner(Long auctionId);
    Bid getTheHighestBid(Auction auction);
}
