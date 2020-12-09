package edu.miu.cs.neptune.controller;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import edu.miu.cs.neptune.domain.Auction;
import edu.miu.cs.neptune.domain.AuctionOrder;
import edu.miu.cs.neptune.domain.AuctionStatus;
import edu.miu.cs.neptune.domain.Bid;
import edu.miu.cs.neptune.domain.Product;
import edu.miu.cs.neptune.service.AuctionService;
import edu.miu.cs.neptune.service.impl.PaypalService;
import edu.miu.cs.neptune.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/auction")
public class AuctionController {

    @Autowired
    PaypalService paypalService;

    public static final String SUCCESS_URL = "/pay/success";
    public static final String CANCEL_URL = "/pay/cancel";


    @Autowired
    AuctionService auctionService;

    @Autowired
    private ProductService productService;

    // Ganzo, bid history with pagination
    @GetMapping(value = "/{id}")
    public String bidHistory(@PathVariable("id") Long auctionId, Model model) {
//        System.out.println("auctionId:"+auctionId);
        if (auctionService.getById(auctionId).isPresent()) {
            Auction currAuction = auctionService.getById(auctionId).get();
            // bidirectional
            currAuction.getProduct().setAuction(currAuction);
            model.addAttribute("auction", currAuction);
//            System.out.println("product: "+currAuction.getProduct().getProductName());
//            System.out.println("auction id is:"+currAuction.getAuctionId()+' '+currAuction.getBeginPrice());
//            for (Bid theBid : currAuction.getBids()) {
//                System.out.println(theBid);
//            }

        }
//        else {
//            System.out.println("can't find the auction");
//        }

        return "bidHistory";
    }

    @GetMapping(value = "/")
    public String showAllAuctions(Model model) {
        model.addAttribute("auctions", auctionService.getAllByUserId(4L));
        return "auctionHistory";
    }

    // review before payment, should provide shipping address
    @GetMapping(value = "/pay")
    public String beforePayment(@RequestParam String auctionId, Model model) {
        AuctionOrder auctionOrder =  paypalService.getAuctionOrder(Long.parseLong(auctionId));
        if (auctionOrder==null) {
            return "error";
        }
        auctionOrder.setPrice(2.0);
        //System.out.println(auctionOrder);
        model.addAttribute("auctionOrder", auctionOrder);
        return "reviewPayment";
    }

    @PostMapping(value = "/pay")
    public String doPayment(@ModelAttribute("order") AuctionOrder auctionOrder) {

        System.out.println(auctionOrder);
        try {
            Payment payment = paypalService.createPayment(auctionOrder.getPrice(), auctionOrder.getCurrency(), auctionOrder.getMethod(), auctionOrder.getIntent(),
                    auctionOrder.getDescription(), "http://localhost:9999/auction"+CANCEL_URL, "http://localhost:9999/auction"+SUCCESS_URL);
            for(Links link : payment.getLinks()) {
                if (link.getRel().equals("approval_url")) {
                    return "redirect:"+link.getHref();
                }
            }
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }

    @GetMapping(value = CANCEL_URL)
    public String cancelPay() {
        return "cancel";
    }

    @GetMapping(value = SUCCESS_URL)
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")) {
                return "success";
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/";
    }

    @GetMapping("/inputAuction")
    public String inputAuction(@ModelAttribute("auction") Auction auction, @RequestParam("productId") Long productId, Model model) {
        model.addAttribute("product", productService.getProductById(productId));
        return "auction/AuctionForm";
    }

    @PostMapping("/saveAuction")
    public String saveAuction(@ModelAttribute("auction") Auction auction, @RequestParam("productId") Long productId, Model model, BindingResult result) {
        if (result.hasErrors()) {
            return "auction/AuctionForm";
        }

        Product product = productService.getProductById(productId);
        auction.setProduct(product);
        auction.setAuctionStatus(AuctionStatus.ACTIVE);
        auctionService.save(auction);
        model.addAttribute("auction", auction);
        return "auction/AuctionDetail";
     }

}
