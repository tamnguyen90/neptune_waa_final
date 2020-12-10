package edu.miu.cs.neptune.controller;

import edu.miu.cs.neptune.domain.User;
import edu.miu.cs.neptune.domain.UserVerificationType;
import edu.miu.cs.neptune.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
public class LoginController {
    @Autowired
    UserService userService;

    @GetMapping(value = "/login")
    public String loginGet(@RequestParam(value = "error", required = false) String error,
                           @RequestParam(value = "logout", required = false) String logout,
                           Model model) {
        String errorMessge = null;
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
        System.out.println(user.getEmail());
        if (user != null && UserVerificationType.NEED_TO_VERIFY.equals(user.getUserVerificationType())) {
            model.addAttribute("userId", user.getUserId());
            return "redirect:/verification";
        } else {
            return "redirect:/index";
        }
    }

    @GetMapping("/index")
    public String getIndex(Model model) {
        return "index";
    }

    @PostMapping("/resendVerificationCode")
    public String resendVerificationCodePost(Authentication authentication, Model model) {
        User user = userService.getByName(authentication.getName()).orElse(null);
        String mailSubject = "Resend verification code";
        userService.generateVerificationCode(user);
        userService.sendVerificationCode(mailSubject, user);
        userService.updateUser(user);
        return "redirect:/login";
    }

    @GetMapping("/verification")
    public String getVerification(Model model) {
        return "verification1";
    }

    @PostMapping("/verification")
    public String postVerification(@RequestParam(value = "verificationCode", required = false) String verificationCode,
                                   Authentication authentication,
                                   Model model) {
        User user = userService.getByName(authentication.getName()).orElse(null);
        if (verificationCode != null && user.getVerificationCreationTime().isBefore(LocalDateTime.now())) {
            return "redirect:/login";
        } else if (verificationCode != null && verificationCode.equals(user.getVerificationCode())) {
            user.setUserVerificationType(UserVerificationType.VERIFIED);
            userService.updateUser(user);
            return "redirect:/index";
        } else if (user.getFailedVerificationCount() < 3) {
            user.increaseFailedVerificationCount();
            userService.updateUser(user);
            return "redirect:/verification";
        } else {
            user.resetFailedVerificationCount();
            userService.updateUser(user);
            return "redirect:/login";
        }
    }

    @GetMapping("/denied")
    public String accessDenied() {
        return "accessDenied";
    }
}
