package edu.miu.cs.neptune.facade;

import edu.miu.cs.neptune.domain.Auction;
import edu.miu.cs.neptune.domain.Bid;
import edu.miu.cs.neptune.domain.User;

import java.util.List;

public interface AuctionFacade {
    Bid createBid(Bid bid, User user);
    List<Bid> getUserBids(User user);
    Auction closeAuction(Auction auction);
    boolean returnDeposit(Auction auction);
    User winner(Auction auction);


}
