package edu.miu.cs.neptune.controller;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import edu.miu.cs.neptune.domain.*;
import edu.miu.cs.neptune.facade.AuctionFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@SessionAttributes({"sessionAuctionOrder"})
@RequestMapping("/auction")
public class AuctionController {

    public static final String SUCCESS_URL = "/pay/success";
    public static final String CANCEL_URL = "/pay/cancel";

    @Autowired
    private AuctionFacade auctionFacade;

    // Ganzo, bid history with pagination
    @GetMapping(value = "/{id}")
    public String bidHistory(@PathVariable("id") Long auctionId, Model model) {
//        System.out.println("auctionId:"+auctionId);
        Auction currAuction = auctionFacade.getAuctionById(auctionId);
        if (currAuction != null) {
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
        model.addAttribute("auctions", auctionFacade.getAllAuctionsByUserId(4L));
        return "auctionHistory";
    }

    // review before payment, should provide shipping address
    @GetMapping(value = "/pay")
    public String beforePayment(@RequestParam String auctionId, Model model) {
        AuctionOrder auctionOrder =  auctionFacade.getAuctionOrderByAuctionId(Long.parseLong(auctionId));
        if (auctionOrder==null) {
            return "error";
        }
        auctionOrder.setPrice(2.0);
        //System.out.println(auctionOrder);
        model.addAttribute("auctionOrder", auctionOrder);
        return "reviewPayment";
    }

    @PostMapping(value = "/pay")
    public String doPayment(@ModelAttribute("order") AuctionOrder auctionOrder, HttpSession session, Model model) {

        session.setAttribute("sessionAuctionOrder", auctionOrder);
        System.out.println(auctionOrder);
        try {
            Payment payment = auctionFacade.createPayment(auctionOrder.getPrice(), auctionOrder.getCurrency(), auctionOrder.getMethod(), auctionOrder.getIntent(),
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
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId, Model model, HttpSession session) {
        try {
            Payment payment = auctionFacade.executePayment(paymentId, payerId);
            System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")) {
                model.addAttribute("auctionOrder", session.getAttribute("sessionAuctionOrder"));
                System.out.println(session.getAttribute("sessionAuctionOrder"));
                session.removeAttribute("sessionAuctionOrder");
                return "invoice";
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/";
    }

     @GetMapping("/activeAuctions")
     public String getActiveAuctions(Model model) {
        List<Auction> activeAuctions = auctionFacade.getALlActiveAuctions();
        model.addAttribute("activeAuctions", activeAuctions);

        return "auction/ActiveAuctions";
     }

     @GetMapping("/auction")
     public String auctionDetail(@RequestParam("id") Long auctionId, Model model) {
        Auction auction = auctionFacade.getAuctionById(auctionId);
        model.addAttribute("auction", auction);
        Bid highestBid = auctionFacade.getTheHighestBid(auction);
        Double highestPrice = highestBid != null ? highestBid.getBiddingAmount() : auction.getBeginPrice();
        model.addAttribute("highestBid", highestPrice);

        return "auction/AuctionDetail";
     }

     @PostMapping("/bidding")
    public String bidding(@RequestParam("amount") Double bidAmount,
                          @RequestParam("auctionId") Long auctionId,
                          Principal principal,
                          Model model) {
        User currentUser = auctionFacade.getUserByUserName(principal.getName());
        if (currentUser == null) {
            throw new RuntimeException("The user not found.");
        }
        if (!UserVerificationType.VERIFIED.equals(currentUser.getUserVerificationType())) {
            throw new RuntimeException("The user need to be verified to be able to bid on the product.");
        }

        Auction auction = auctionFacade.getAuctionById(auctionId);
        if (currentUser.getUsername().equals(auction.getProduct().getSeller().getUsername())) {
            throw new RuntimeException("The sellers can't bid on their own products.");
        }

        Bid highestBid = auctionFacade.getTheHighestBid(auction);
        if (bidAmount < auction.getBeginPrice() || (highestBid != null && bidAmount <= highestBid.getBiddingAmount())) {
            throw new RuntimeException("The bid amount must greater than the initial price or the current bid price.");
        }

        Bid newBid = new Bid(bidAmount, LocalDateTime.now());

        newBid = auctionFacade.createBid(newBid, currentUser, auction);

        return "redirect:/auction/auction?id="+ auctionId;
     }

}
