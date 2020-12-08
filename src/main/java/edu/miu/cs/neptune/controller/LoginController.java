package edu.miu.cs.neptune.controller;

import edu.miu.cs.neptune.domain.User;
import edu.miu.cs.neptune.domain.UserVerification;
import edu.miu.cs.neptune.domain.UserVerificationType;
import edu.miu.cs.neptune.service.UserService;
import edu.miu.cs.neptune.service.UserVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @Autowired
    UserService userService;
    @Autowired
    UserVerificationService userVerificationService;

   @GetMapping(value = "/login")
    public String loginGet(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            Model model) {
        String errorMessge = null;
       System.out.println(error);
        if (error != null) {
            errorMessge = "Username or Password is incorrect !!";
        }
        if (logout != null) {
            errorMessge = "You have been successfully logged out !!";
        }
        model.addAttribute("errorMessge", errorMessge);
        return "login";
    }
        @GetMapping(value = {"/postlogin"})
    public String postLogin(Authentication authentication, Model model) {
        User user = userService.getByName(authentication.getName()).orElse(null);
        if (user != null) {
            UserVerification userVerification = userVerificationService.getByUserId(user.getUserId()).orElse(null);
            if (userVerification != null && UserVerificationType.NEED_TO_VERIFY.equals(userVerification.getUserVerificationType())) {
                model.addAttribute("verificationCode", userVerification.getVerificationCode());
                model.addAttribute("email", user.getEmail());
                return "verification";
            } else return "redirect:/verification";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/verification")
    public String getVerification() {
        return "verification";
    }

    @PostMapping("/verification")
    public String postVerification() {
        return "verification";
    }

    @GetMapping("/denied")
    public String accessDenied(){
        return "accessDenied";
    }
}
