package edu.miu.cs.neptune.controller;

import edu.miu.cs.neptune.domain.User;
import edu.miu.cs.neptune.domain.UserVerificationType;
import edu.miu.cs.neptune.service.UserService;
import edu.miu.cs.neptune.service.UserVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    @Autowired
    UserService userService;
    @Autowired
    UserVerificationService userVerificationService;

    @GetMapping(value = {"/","/login"})
    public String getLogin() {
        return "login";
    }

    @PostMapping(value ={"/login"})
    public String loginVerification(@RequestParam("username") String userName, Model model){

        if(userService.getByName(userName)!=null){
            Long userId = userService.getByName(userName).get().getUserId();
            if(userVerificationService.getByUserId(userId).get().equals(UserVerificationType.NEED_TO_VERIFY)){
                String useEmail = userService.getByName(userName).get().getEmail();
                model.addAttribute("email",useEmail);
            return "verification";}
            else return "home";
        }
        else {
            return "login";
        }

    }
}
