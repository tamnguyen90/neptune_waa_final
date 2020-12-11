package edu.miu.cs.neptune.service.impl;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import edu.miu.cs.neptune.domain.*;
import edu.miu.cs.neptune.repository.AuctionRepository;
import edu.miu.cs.neptune.service.AuctionService;
import edu.miu.cs.neptune.service.PaypalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Service
public class PaypalServiceImpl implements PaypalService {

    @Autowired
    AuctionService auctionService;

    @Autowired
    private APIContext apiContext;

    public AuctionOrder getAuctionOrder(Long auctionId) {
        Optional<Auction> auction = auctionService.getById(auctionId);

        if (auction.isPresent()) {
            Auction theAuction = auction.get();
            Product theProduct = theAuction.getProduct();

            Comparator<Bid> cmp = Comparator.comparing(Bid::getBiddingAmount);
            Bid highestBid = Collections.max(theAuction.getBids(), Comparator.comparing(b -> b.getBiddingAmount()));
            User theUser = highestBid.getBidder();

            // create paypal order object
            AuctionOrder auctionOrder = new AuctionOrder();
            auctionOrder.setDescription(theProduct.getProductName());
            auctionOrder.setPrice(highestBid.getBiddingAmount());
            auctionOrder.setUser(theUser);
            auctionOrder.setCurrency("USD");
            auctionOrder.setMethod("paypal");
            auctionOrder.setIntent("SALE");
            return auctionOrder;
        }
        return null;
    }

    public Payment createPayment(
            Double total,
            String currency,
            String method,
            String intent,
            String description,
            String cancelUrl,
            String successUrl) throws PayPalRESTException {
        Amount amount = new Amount();
        amount.setCurrency(currency);
        total = new BigDecimal(total).setScale(2, RoundingMode.HALF_UP).doubleValue();
        amount.setTotal(String.format("%.2f", total));

        Transaction transaction = new Transaction();
        transaction.setDescription(description);
        transaction.setAmount(amount);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod(method.toString());

        Payment payment = new Payment();
        payment.setIntent(intent.toString());
        payment.setPayer(payer);
        payment.setTransactions(transactions);
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(cancelUrl);
        redirectUrls.setReturnUrl(successUrl);
        payment.setRedirectUrls(redirectUrls);

        return payment.create(apiContext);
    }

    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException{
        Payment payment = new Payment();
        payment.setId(paymentId);
        PaymentExecution paymentExecute = new PaymentExecution();
        paymentExecute.setPayerId(payerId);
        return payment.execute(apiContext, paymentExecute);
    }

    public Refund refundPayment(String saleId, double refundAmount) throws PayPalRESTException {
        Refund refund = new Refund();
        Amount amount = new Amount();
        amount.setTotal(Double.toString(refundAmount));
        amount.setCurrency("USD");
        refund.setAmount(amount);

        Sale sale = new Sale();
        sale.setId(saleId);

        return sale.refund(apiContext, refund);
    }

    public void sendFundToSeller() {
        //Todo return the fund back to people who lost the auction.
    }

}
