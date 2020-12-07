package edu.miu.cs.neptune.service.impl;

import edu.miu.cs.neptune.Util.Util;
import edu.miu.cs.neptune.domain.Auction;
import edu.miu.cs.neptune.domain.Bid;
import edu.miu.cs.neptune.domain.User;
import edu.miu.cs.neptune.repository.AuctionRepository;
import edu.miu.cs.neptune.repository.UserRepository;
import edu.miu.cs.neptune.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuctionServiceImpl implements AuctionService {
    @Autowired
    AuctionRepository auctionRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<Auction> getAll() {
        return Util.iterableToCollection(auctionRepository.findAll());
    }

    @Override
    public Optional<Auction> getById(Long id) {
        //Todo get auction by id;
        Optional<Auction> auction = auctionRepository.findById(id);
        return auction;
    }

    @Override
    public List<Auction> getAllByUserId(Long userId) {
//        Optional<User> theUser = userRepository.findById(userId);
//
//        if (theUser.isPresent()) {
//            List<Bid> userBidList = theUser.get().getBids();
//        }
        return null;
    }
}
