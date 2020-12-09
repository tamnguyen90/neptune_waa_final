package edu.miu.cs.neptune.controller;

import edu.miu.cs.neptune.domain.Role;
import edu.miu.cs.neptune.domain.User;
import edu.miu.cs.neptune.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/create")
    public String createUserGet(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("availableRoles", Arrays.asList(Role.values()));
//        return "user/create";
        return "user/createUser";
    }

    @PostMapping(value = "/create")
    public String createUserPost(User user) {
        userService.saveUser(user);
        return "redirect:/login";
    }

    @RequestMapping("/resetPassword")
    public String resetPasswordGet(Model model) {
        return "resetPassword";
    }

    @PostMapping("/resetPassword")
    public String resetPasswordPost(@RequestParam(value = "username", required = false) String username,
                                    @RequestParam(value = "email", required = false) String email,
                                    @RequestParam(value = "newPassword", required = false) String newPassword,
                                    @RequestParam(value = "verifyNewPassword", required = false) String verifyNewPassword,
                                    Model model) {
        User user = userService.getByName(username).orElse(null);
        if (user == null) {
            String userError = "Input user is not exist";
            model.addAttribute("errorMessage",userError);
            return "resetPassword";
        } else if (!email.equals(user.getEmail())) {
            String emailError = "Email input is not correct";
            model.addAttribute("errorMessage",emailError);
            return "resetPassword";
        } else if(!newPassword.equals(verifyNewPassword)){
            String passwordError = "Input Passwords are not matched";
            model.addAttribute("errorMessage",passwordError);
            return "resetPassword";
        }else{
            user.setPassword(newPassword);
            userService.updatePassword(user);
            return "redirect:/login";
        }
    }
}
