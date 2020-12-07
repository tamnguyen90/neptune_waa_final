package edu.miu.cs.neptune.repository;

import edu.miu.cs.neptune.domain.SystemPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemPaymentRepository extends JpaRepository<SystemPayment, Long> {
}
