package edu.miu.cs.neptune.repository;

import edu.miu.cs.neptune.domain.Shipping;
import edu.miu.cs.neptune.domain.SystemPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SystemPaymentRepository extends JpaRepository<SystemPayment, Long> {

    @Query("SELECT p FROM SystemPayment p WHERE p.auctionId=:auctionId")
    public List<SystemPayment> findByAuctionId(@Param("auctionId") Long auctionId);
}
