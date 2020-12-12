package edu.miu.cs.neptune.repository;

import edu.miu.cs.neptune.domain.PaymentStatus;
import edu.miu.cs.neptune.domain.SystemPayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SystemPaymentRepository extends JpaRepository<SystemPayment, Long> {
    public Optional<SystemPayment> findSystemPaymentByAuctionIdAndAndUserIdAndAndPaymentStatus(Long auctionId, Long userId, PaymentStatus paymentStatus);
}
