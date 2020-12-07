package edu.miu.cs.neptune.repository;

import edu.miu.cs.neptune.domain.Bid;
import edu.miu.cs.neptune.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BiddingRepository extends JpaRepository<Bid, Long> {

}
