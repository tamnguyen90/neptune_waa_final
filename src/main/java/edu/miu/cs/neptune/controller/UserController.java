package edu.miu.cs.neptune.controller;

import edu.miu.cs.neptune.domain.Role;
import edu.miu.cs.neptune.domain.User;
import edu.miu.cs.neptune.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    public String getCreateUser(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("availableRoles", Arrays.asList(Role.values()));
        return "user/create";
    }

    @PostMapping(value = "/create")
    public String postCreateUser(User user) {
        userService.saveUser(user);
        return "redirect:/login";
    }

}
