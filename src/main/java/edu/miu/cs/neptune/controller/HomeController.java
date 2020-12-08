package edu.miu.cs.neptune.controller;

import edu.miu.cs.neptune.domain.User;
import edu.miu.cs.neptune.domain.UserVerification;
import edu.miu.cs.neptune.domain.UserVerificationType;
import edu.miu.cs.neptune.service.UserService;
import edu.miu.cs.neptune.service.UserVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@SessionAttributes({"username"})
@Controller
public class HomeController {
    @Autowired
    UserService userService;
    @Autowired
    UserVerificationService userVerificationService;

    @GetMapping(value = {"/login"})
    public String getLogin(Model model) {
        if (model.asMap().get("username") != null) {
            return "forward:/";
        } else return "login";
    }

    @PostMapping(value = {"/login"})
    public String loginVerification(@RequestParam("username") String userName, Model model) {
        User user = userService.getByName(userName).orElse(null);
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

    @PostMapping("/verification")
    public String returnVerificationPage() {
        return "verification";
    }

}
