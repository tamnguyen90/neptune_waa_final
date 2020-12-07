package edu.miu.cs.neptune.service.impl;

import edu.miu.cs.neptune.Util.Util;
import edu.miu.cs.neptune.domain.Auction;
import edu.miu.cs.neptune.repository.AuctionRepository;
import edu.miu.cs.neptune.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuctionServiceImpl implements AuctionService {
    @Autowired
    AuctionRepository auctionRepository;

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
}
