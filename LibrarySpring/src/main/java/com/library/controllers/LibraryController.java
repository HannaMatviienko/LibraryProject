package com.library.controllers;

import com.library.dto.UserDTO;
import com.library.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

@Controller
public class LibraryController {

    private final UserService userService;

    @Autowired
    public LibraryController(UserService userService) {
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
                    return "redirect:/order";
                case "ROLE_ADMIN":
                    return "redirect:/admin";
                default:
                    return "index";
            }
        }
    }

    @GetMapping("/home")
    public String getMain(Model model, HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDTO user = userService.findUserByEmail(auth.getName());

        switch (user.getRole())
        {
            case "ROLE_ADMIN":
                return "redirect:/admin/users";
            case "ROLE_LIBRARIAN":
                return "redirect:/librarian/index";
            case "ROLE_USER":
                return "redirect:/user";
            default:
                return "redirect:/";
        }
    }
}