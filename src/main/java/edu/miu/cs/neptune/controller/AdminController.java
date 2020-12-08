package edu.miu.cs.neptune.controller;

import edu.miu.cs.neptune.domain.User;
import edu.miu.cs.neptune.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;

    //List not verified profile
    @RequestMapping(value = "/profile_review_list")
    public String listPendingProfile(Model model) {
        List<User> users = userService.findAllPendingProfile();
        model.addAttribute("users", users);

        return "admin/PendingProfileList";
    }
}
