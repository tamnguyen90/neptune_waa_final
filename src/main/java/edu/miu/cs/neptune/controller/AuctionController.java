package edu.miu.cs.neptune.controller;

import com.paypal.api.payments.Authorization;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import edu.miu.cs.neptune.domain.*;
import edu.miu.cs.neptune.facade.AuctionFacade;
import edu.miu.cs.neptune.Util.Util;
import edu.miu.cs.neptune.service.SystemPaymentService;
import edu.miu.cs.neptune.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@SessionAttributes({"sessionAuctionOrder"})
@RequestMapping("/auction")
public class AuctionController {

    private static final String PAY_SUCCESS = "/pay/success";
    private static final String DEPOSIT_SUCCESS = "/deposit/success";
    private static final String CANCEL_URL = "/pay/cancel";

    @Autowired
    private UserService userService;


    @Autowired
    private SystemPaymentService systemPaymentService;


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

    @GetMapping(value="")
    public String showAllAuctions(Model model, Principal principal) {
        System.out.println("username:"+principal.getName());
        Optional<User> user = userService.getByName(principal.getName());

        if (user.isPresent()) {
            User theUser = user.get();
            List<Auction> listAuction;
            if (theUser.getRole() == Role.BUYER) {
                listAuction = auctionFacade.getAllAuctionsByUserId(theUser.getUserId());
                model.addAttribute("auctions", listAuction);
                model.addAttribute("user", theUser);
                return "auctionHistory";
            } else {
                listAuction = auctionFacade.getAllAuctionsBySellerId(theUser.getUserId());
                model.addAttribute("auctions", listAuction);
                model.addAttribute("user", theUser);
                return "auctionHistorySeller";
            }
        }
        System.out.println("Error: user not found");
        return "error";
    }

    @GetMapping(value="/invoice")
    public String showInvoice(@RequestParam String auctionId, @RequestParam String userId, Model model, Principal principal) {

        System.out.println("auctionId:"+auctionId+" userId:"+userId);
        Optional<User> user = userService.getById(Long.parseLong(userId));
        if (user.isPresent()) {
            User theUser = user.get();
            if (!theUser.getUsername().equals(principal.getName())) {
                return "error";
            }
            System.out.println("user is present");
            List<Invoice> listOfInvoices = theUser.getInvoices();
            System.out.println("number of List:"+listOfInvoices.size());
            for (Invoice invoice : listOfInvoices) {
                if (invoice.getAuctionId() == Long.parseLong(auctionId)) {
                    System.out.println(theUser.getAddress());
                    model.addAttribute("user", theUser);
                    model.addAttribute("invoice", invoice);
                    return "Invoice";
                }
            }
        }
        return "error";
    }

    @GetMapping(value="/ship")
    public String shipItem(@RequestParam String auctionId, Principal principal) {
        Auction auction = auctionFacade.getAuctionById(Long.parseLong(auctionId));
        Optional<User> user = userService.getByName(principal.getName());
        if (user.isPresent()) {
            if (auction.getProduct().getSeller().getUserId() == user.get().getUserId()) {
                // only seller can ship the item
                auction.setShippingDate(LocalDateTime.now());
                auction.setShippingStatus(ShippingStatus.IN_TRANSIT);
                auctionFacade.saveAuction(auction);
//                Shipping shipping = new Shipping();
//                shipping.setUserId(user.get().getUserId());
//                shipping.setSellerId(auction.getProduct().getSeller().getUserId());
//                shipping.setProductId(auction.getProduct().getProductId());
//                shipping.setShippingStatus(ShippingStatus.IN_TRANSIT);
//                shipping.setShippingDate(LocalDate.now());
//                shippingService.save(shipping);
            }
        }
        return "redirect:";
    }

    @GetMapping(value="/shippingReceived")
    public String shippingReceived(@RequestParam String auctionId, Principal principal) {
        Auction auction = auctionFacade.getAuctionById(Long.parseLong(auctionId));
        Optional<User> user = userService.getByName(principal.getName());
        if (user.isPresent()) {
            if (auction.getWinnerId() == user.get().getUserId()) {
                // buyer should be the winner
                auction.setShippingStatus(ShippingStatus.DELIVERED);
                auctionFacade.saveAuction(auction);
            }
        }
        return "redirect:";
    }

    // review before payment, should provide shipping address
    @GetMapping(value = "/pay")
    public String beforePayment(@RequestParam String auctionId, Model model, Principal principal) {
        AuctionOrder auctionOrder =  auctionFacade.getAuctionOrderByAuctionId(Long.parseLong(auctionId), principal.getName());
        if (auctionOrder==null) {
            return "redirect:/auction?error=1";
        }
        auctionOrder.setPrice(2.0);
        //System.out.println(auctionOrder);

        model.addAttribute("auctionOrder", auctionOrder);
        return "reviewPayment";
    }

    @GetMapping(value = "/deposit")
    public String beforeDeposit(@RequestParam("auctionId") Long auctionId, Model model, Principal principal) {
        AuctionOrder auctionOrder = auctionFacade.getDepositAuctionOrder(auctionId, principal.getName());
        if (auctionOrder==null) {
            return "redirect:/auction?error=1";
        }
        model.addAttribute("auctionOrder", auctionOrder);
        model.addAttribute("auction", auctionFacade.getAuctionById(auctionId));
        return "auction/DepositPaymentReview";
    }

    @PostMapping(value = "/deposit")
    public String doDeposit(@ModelAttribute("order") AuctionOrder auctionOrder, HttpSession session, Model model) {

        session.setAttribute("sessionAuctionOrder", auctionOrder);
        auctionOrder.setIntent("authorize");
        System.out.println(auctionOrder);
        try {
            Payment payment = auctionFacade.createPayment(auctionOrder.getPrice(), auctionOrder.getCurrency(), auctionOrder.getMethod(), auctionOrder.getIntent(),
                    auctionOrder.getDescription(), "http://localhost:9999/auction"+CANCEL_URL, "http://localhost:9999/auction"+ DEPOSIT_SUCCESS);
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

    @GetMapping(value = DEPOSIT_SUCCESS)
    public String depositSuccess(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId, Model model, HttpSession session) {
        try {
            Payment payment = auctionFacade.executePayment(paymentId, payerId);
            System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")) {
                AuctionOrder theAuctionOrder = (AuctionOrder)session.getAttribute("sessionAuctionOrder");

                Authorization authorization = payment.getTransactions().get(0).getRelatedResources().get(0).getAuthorization();

                String saleId = authorization.getId(); //Util.parseJSONSaleId(payment.toJSON());
                System.out.println("AuthorizationId:" + saleId);

                SystemPayment systemPayment = new SystemPayment(theAuctionOrder.getAuctionId(), theAuctionOrder.getUser().getUserId(), theAuctionOrder.getPrice(),
                        PaymentStatus.DEPOSITED, PaymentType.PRODUCT_PAYMENT, saleId);
                // store payment information in the database;
                auctionFacade.savePayment(systemPayment);

                model.addAttribute("auctionOrder", theAuctionOrder);
                session.removeAttribute("sessionAuctionOrder");
                return "auction/DepositSuccess";
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/";
    }

    @PostMapping(value = "/pay")
    public String doPayment(@ModelAttribute("order") AuctionOrder auctionOrder, HttpSession session, Model model) {

        session.setAttribute("sessionAuctionOrder", auctionOrder);
        auctionOrder.setIntent("authorize");
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
//        model.addAttribute("auctionOrder",auctionOrder);
//        return "invoice";
    }

    @GetMapping(value = CANCEL_URL)
    public String cancelPay() {
        return "cancel";
    }

    @GetMapping(value = SUCCESS_URL)
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId, Model model, HttpSession session, Principal principal) {
        try {
            Payment payment = auctionFacade.executePayment(paymentId, payerId);
            System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")) {
                AuctionOrder theAuctionOrder = (AuctionOrder)session.getAttribute("sessionAuctionOrder");

                Authorization authorization = payment.getTransactions().get(0).getRelatedResources().get(0).getAuthorization();

                String saleId = authorization.getId(); //Util.parseJSONSaleId(payment.toJSON());
                System.out.println("AuthorizationId:" + saleId);

                SystemPayment systemPayment = new SystemPayment(theAuctionOrder.getAuctionId(), theAuctionOrder.getUser().getUserId(), theAuctionOrder.getPrice(),
                        PaymentStatus.PAID, PaymentType.PRODUCT_PAYMENT, saleId);
                // store payment information in the database;
                auctionFacade.savePayment(systemPayment);
                auctionFacade.productSold(theAuctionOrder.getAuctionId());

                model.addAttribute("auctionOrder", theAuctionOrder);
                Invoice invoice = new Invoice(LocalDate.now(), theAuctionOrder.getPrice(), theAuctionOrder.getUser().getUserId(), theAuctionOrder.getAuctionId());

                Optional<User> user = userService.getByName(principal.getName());
                if (user.isPresent()) {
                    user.get().getInvoices().add(invoice);
                    userService.updateUser(user.get());
                }

                session.removeAttribute("sessionAuctionOrder");
                return "orderConfirmation";
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/";
    }

    @GetMapping("/capturePaypal")
    public String capturePaypal(){
        //auctionFacade.finalizePayment("3C160388E42030817", 2.0);
        auctionFacade.cancelPayment("3C160388E42030817");
        return "success";
    }

     @GetMapping("/activeAuctions")
     public String getActiveAuctions(Model model) {
        List<Auction> activeAuctions = auctionFacade.getALlActiveAuctions();
        model.addAttribute("activeAuctions", activeAuctions);

        return "auction/ActiveAuctions";
     }

     @GetMapping("/auction")
     public String auctionDetail(@RequestParam("id") Long auctionId, Principal principal, Model model) {
        Auction auction = auctionFacade.getAuctionById(auctionId);
        Bid highestBid = auctionFacade.getTheHighestBid(auction);
        Double highestPrice = highestBid != null ? highestBid.getBiddingAmount() : auction.getBeginPrice();
        User currentUser = auctionFacade.getUserByUserName(principal.getName());
        SystemPayment depositPaymemt = auctionFacade.getDepositPaymentByUser(currentUser, auction);
        boolean isSeller = currentUser.getUsername().equals(auction.getProduct().getSeller().getUsername()) ? true : false;
        boolean isUserVerified = UserVerificationType.VERIFIED.equals(currentUser.getUserVerificationType()) ? true : false;
        model.addAttribute("auction", auction);
        model.addAttribute("highestBid", highestPrice);
        model.addAttribute("isSeller", isSeller);
        model.addAttribute("isUserVerified", isUserVerified);
        model.addAttribute("isDeposited", depositPaymemt != null ? true : false);

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
