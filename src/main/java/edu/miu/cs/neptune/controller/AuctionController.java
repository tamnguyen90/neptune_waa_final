package edu.miu.cs.neptune.controller;

import edu.miu.cs.neptune.domain.Auction;
import edu.miu.cs.neptune.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class AuctionController {

    @Autowired
    AuctionService auctionService;

    // Ganzo, bid history with pagination
    @GetMapping(value = "/auction/{id}")
    public String bidHistory(@PathVariable("id") Long auctionId, Model model) {
        System.out.println("auctionId:"+auctionId);
        if (auctionService.getById(auctionId).isPresent()) {
            Auction currAuction = auctionService.getById(auctionId).get();
            model.addAttribute("auction", auctionService.getById(auctionId).get());
            Auction auction = auctionService.getById(auctionId).get();
            System.out.println("auction id is:"+auction.getAuctionId()+' '+auction.getBeginPrice());
            System.out.println("product: "+auction.getProduct().getProductName());
        } else {
            System.out.println("can't find the auction");
        }

        return "index";
    }
}
