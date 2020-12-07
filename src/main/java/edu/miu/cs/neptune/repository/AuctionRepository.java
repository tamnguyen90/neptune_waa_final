package edu.miu.cs.neptune.repository;

import edu.miu.cs.neptune.domain.Auction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuctionRepository extends CrudRepository<Auction, Long> {
}
