package edu.miu.cs.neptune.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller


public class CustomerController {
    @GetMapping("/hello")
    public String welcome(){
        return "index";
    }
}
