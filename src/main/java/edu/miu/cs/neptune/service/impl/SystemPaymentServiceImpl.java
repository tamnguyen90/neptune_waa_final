package edu.miu.cs.neptune.service.impl;

import edu.miu.cs.neptune.domain.SystemPayment;
import edu.miu.cs.neptune.repository.SystemPaymentRepository;
import edu.miu.cs.neptune.service.SystemPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemPaymentServiceImpl implements SystemPaymentService {

    @Autowired
    private SystemPaymentRepository systemPaymentRepository;

    @Override
    public List<SystemPayment> getAll() {
        return null;
    }

    @Override
    public SystemPayment getById(Long id) {
        return null;
    }

    @Override
    public List<SystemPayment> getPaymentsByAuction(Long auctionId) {
        return null;
    }
}