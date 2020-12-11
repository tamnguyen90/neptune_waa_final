package edu.miu.cs.neptune.service;

import com.paypal.api.payments.Payment;
import com.paypal.api.payments.Refund;
import com.paypal.base.rest.PayPalRESTException;
import edu.miu.cs.neptune.domain.AuctionOrder;

public interface PaypalService {
    Refund refundPayment(String saleId, double refundAmount) throws PayPalRESTException;
    void sendFundToSeller();
    AuctionOrder getAuctionOrder(Long auctionId);
    Payment executePayment(String paymentId, String payerId) throws PayPalRESTException;
    Payment createPayment(
            Double total,
            String currency,
            String method,
            String intent,
            String description,
            String cancelUrl,
            String successUrl) throws PayPalRESTException;
}
