package edu.miu.cs.neptune.service.impl;

import edu.miu.cs.neptune.Util.Util;
import edu.miu.cs.neptune.domain.*;
import edu.miu.cs.neptune.repository.AuctionRepository;
import edu.miu.cs.neptune.repository.UserRepository;
import edu.miu.cs.neptune.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        return auctionRepository.findById(id);
    }

    @Override
    public Auction save(Auction auction) {
        return auctionRepository.save(auction);
    }

    @Override
    public List<Auction> getAllByUserId(Long userId) {
        List<Auction> auctionList = getAll();
        List<Auction> userAuctionList = new ArrayList<>();
        for (Auction theAuction : auctionList) {
            for (Bid theBid : theAuction.getBids()) {
                if (theBid.getBidder().getUserId() == userId) {
                    userAuctionList.add(theAuction);
                    break;
                }
            }
        }
        return userAuctionList;
    }

    @Override
    public List<Auction> getAllEndedAuction() {
        List<Auction> auctionList = getAll();
        return auctionList.stream().filter(e -> e.getAuctionStatus()==AuctionStatus.ENDED).collect(Collectors.toList());
    }

    @Override
    public void productSold(Long id) {
        Optional<Auction> auction = auctionRepository.findById(id);

        if (auction.isPresent()) {
            Auction theAuction = auction.get();

            theAuction.getProduct().setProductStatus(ProductStatus.PRODUCT_SOLD);
            auctionRepository.save(theAuction);
        }
    }


//    @Override
//    public List<Auction> getAllByUserId(Long userId) {
////        Optional<User> theUser = userRepository.findById(userId);
////
////        if (theUser.isPresent()) {
////            List<Bid> userBidList = theUser.get().getBids();
////        }
//        return null;
//    }
}
