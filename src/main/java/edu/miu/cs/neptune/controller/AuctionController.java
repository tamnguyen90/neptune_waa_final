package edu.miu.cs.neptune.controller;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import edu.miu.cs.neptune.Util.Util;
import edu.miu.cs.neptune.domain.*;
import edu.miu.cs.neptune.service.AuctionService;
import edu.miu.cs.neptune.service.SystemPaymentService;
import edu.miu.cs.neptune.service.UserService;
import edu.miu.cs.neptune.service.impl.PaypalService;
import edu.miu.cs.neptune.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@SessionAttributes({"sessionAuctionOrder"})
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

    @Autowired
    private UserService userService;


    @Autowired
    private SystemPaymentService systemPaymentService;


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

    @GetMapping(value="")
    public String showAllAuctions(Model model, Principal principal) {
        System.out.println("username:"+principal.getName());
        Optional<User> user = userService.getByName(principal.getName());

        if (user.isPresent()) {
            User theUser = user.get();
            List<Auction> listAuction = auctionService.getAllByUserId(theUser.getUserId());

            for (Auction auction : listAuction) {
                System.out.println(auction.getAuctionStatus());
            }

            model.addAttribute("auctions", listAuction);
            model.addAttribute("user", theUser);

            return "auctionHistory";
        }
        System.out.println("Error: user not found");
        return "error";


    }


    // review before payment, should provide shipping address
    @GetMapping(value = "/pay")
    public String beforePayment(@RequestParam String auctionId, Model model, Principal principal) {
        AuctionOrder auctionOrder =  paypalService.getAuctionOrder(Long.parseLong(auctionId), principal.getName());

        if (auctionOrder==null) {
            return "redirect:/auction?error=1";
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
//        model.addAttribute("auctionOrder",auctionOrder);
//        return "invoice";
    }

    @GetMapping(value = CANCEL_URL)
    public String cancelPay() {
        return "cancel";
    }

    @GetMapping(value = SUCCESS_URL)
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId, Model model, HttpSession session) {
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            //System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")) {
                AuctionOrder theAuctionOrder = (AuctionOrder)session.getAttribute("sessionAuctionOrder");
                String saleId = Util.parseJSONSaleId(payment.toJSON());
                SystemPayment systemPayment = new SystemPayment(theAuctionOrder.getAuctionId(), theAuctionOrder.getUser().getUserId(), theAuctionOrder.getPrice(),
                        PaymentStatus.PAID, PaymentType.PRODUCT_PAYMENT, saleId);
                // store payment information in the database;
                systemPaymentService.save(systemPayment);
                auctionService.productSold(theAuctionOrder.getAuctionId());

                model.addAttribute("auctionOrder", theAuctionOrder);
                session.removeAttribute("sessionAuctionOrder");
                return "invoice";
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
