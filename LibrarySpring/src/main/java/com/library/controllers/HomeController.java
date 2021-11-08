package com.library.controllers;

import com.library.dto.UserDTO;
import com.library.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final UserService userService;

    @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String getIndex() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDTO user = userService.findUserByEmail(auth.getName());
        if (user == null)
            return "index";
        else {
            switch (user.getRole()) {
                case "ROLE_USER":
                    return "redirect:/card";
                case "ROLE_LIBRARIAN":
                    return "redirect:/librarian";
                case "ROLE_ADMIN":
                    return "redirect:/admin/users";
                default:
                    return "index";
            }
        }
    }
}