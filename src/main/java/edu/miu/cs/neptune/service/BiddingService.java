package edu.miu.cs.neptune.service;

import edu.miu.cs.neptune.domain.Bid;
import edu.miu.cs.neptune.domain.User;

import java.util.List;

public interface BiddingService {
    Bid save(Bid bid, User user);
    Bid getBidById(Long bidId);
    List<Bid> getAll();
    List<Bid> getUserBids(User user);

}
