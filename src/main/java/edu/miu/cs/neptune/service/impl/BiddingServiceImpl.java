package edu.miu.cs.neptune.service.impl;

import edu.miu.cs.neptune.Util.Util;
import edu.miu.cs.neptune.domain.Bid;
import edu.miu.cs.neptune.domain.User;
import edu.miu.cs.neptune.repository.BiddingRepository;
import edu.miu.cs.neptune.service.BiddingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BiddingServiceImpl implements BiddingService {
    @Autowired
    private BiddingRepository biddingRepository;

    @Override
    public Bid save(Bid bid, User user) {
        return null;
    }

    @Override
    public Bid getBidById(Long bidId) {
        return null;
    }

    @Override
    public List<Bid> getAll() {
        return Util.iterableToCollection(biddingRepository.findAll());
    }

    @Override
    public List<Bid> getUserBids(User user) {
        return null;
    }
}
