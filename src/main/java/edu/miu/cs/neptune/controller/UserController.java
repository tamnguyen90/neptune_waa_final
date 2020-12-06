package edu.miu.cs.neptune.controller;



import edu.miu.cs.neptune.domain.RoleCode;
import edu.miu.cs.neptune.dto.UserDto;
import edu.miu.cs.neptune.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping(value = "", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    public String list(Model model) {
        model.addAttribute("users", userService.getAll());
        return "user/list";
    }

    @RequestMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public String create(Model model) {
        model.addAttribute("genders", Gender.values());
        model.addAttribute("availableRoles", Arrays.stream(RoleCode.values()).map(rc -> new RoleDto().setCode(rc).setName(rc.getName())).collect(Collectors.toList()));
        return "user/create";
    }

    @RequestMapping("/edit/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    public String edit(@PathVariable String username, Model model) {
        userService.getById(username).ifPresent(u -> {
            model.addAttribute("user", u);
            model.addAttribute("genders", Gender.values());
            model.addAttribute("availableRoles", Arrays.stream(RoleCode.values()).map(rc -> new RoleDto().setCode(rc).setName(rc.getName())).collect(Collectors.toList()));
        });
        return "user/edit";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ADMIN')")
    public String save(UserDto user) {
        userService.update(user);
        return "redirect:/users";
    }

    @RequestMapping("/delete/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@PathVariable String username) {
        userService.delete(username);
        return "redirect:/users";
    }
}
