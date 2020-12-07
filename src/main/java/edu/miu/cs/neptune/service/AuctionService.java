package edu.miu.cs.neptune.service;

import edu.miu.cs.neptune.domain.Auction;
import edu.miu.cs.neptune.domain.Category;

import java.util.List;
import java.util.Optional;

public interface AuctionService {
    public List<Auction> getAll();

    public Optional<Auction> getById(Long id);
}