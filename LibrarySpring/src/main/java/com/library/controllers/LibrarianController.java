package com.library.controllers;

import com.library.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LibrarianController {

    private final UserService userService;

    @Autowired
    public LibrarianController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/librarian/index")
    public String getIndex(Model model, HttpServletRequest request) {
        return "librarian";
    }
}