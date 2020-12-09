package edu.miu.cs.neptune.controller;

import edu.miu.cs.neptune.domain.Auction;
import edu.miu.cs.neptune.domain.AuctionStatus;
import edu.miu.cs.neptune.domain.Bid;
import edu.miu.cs.neptune.domain.Product;
import edu.miu.cs.neptune.service.AuctionService;
import edu.miu.cs.neptune.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/auction")
public class AuctionController {

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
