package edu.miu.cs.neptune.controller;

import edu.miu.cs.neptune.domain.Auction;
import edu.miu.cs.neptune.domain.Bid;
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

    @GetMapping(value = "/auction")
    public String showAllAuctions(Model model) {
        model.addAttribute("auctions", auctionService.getAllByUserId(4L));
        return "auctionHistory";
    }

}
