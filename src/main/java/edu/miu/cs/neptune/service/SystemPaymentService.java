package edu.miu.cs.neptune.service;

import edu.miu.cs.neptune.domain.SystemPayment;

import java.util.List;

public interface SystemPaymentService {
    List<SystemPayment> getAll();
    SystemPayment getById(Long id);
    List<SystemPayment> getPaymentsByAuction(Long auctionId);

}
