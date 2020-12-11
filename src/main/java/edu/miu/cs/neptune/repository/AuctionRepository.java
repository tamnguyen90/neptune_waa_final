package edu.miu.cs.neptune.repository;

import edu.miu.cs.neptune.domain.Auction;
import edu.miu.cs.neptune.domain.AuctionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, Long> {
    List<Auction> findAllByAuctionStatus(AuctionStatus status);
}
