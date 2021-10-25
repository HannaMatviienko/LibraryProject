package com.library.controllers;

import com.library.dto.UserDTO;
import com.library.services.AuthorService;
import com.library.services.UserService;
import com.library.tools.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AdminController {

    private final UserService userService;
    private final AuthorService authorService;

    @Autowired
    public AdminController(UserService userService, AuthorService authorService) {
        this.userService = userService;
        this.authorService = authorService;
    }


    @GetMapping("/admin/publications")
    public String getPublications(Model model, HttpServletRequest request) {
        return "publications";
    }

    @GetMapping("/admin/books")
    public String getBooks(Model model, HttpServletRequest request) {
        return "books";
    }


    @GetMapping("/admin/authors")
    public String getAuthors(Model model) {
        model.addAttribute("authors", authorService.listAll());
        return "authors";
    }

    @GetMapping("/admin/author/new")
    public String newUser(Model model) {
        UserDTO user = new UserDTO();
        user.setRole("ROLE_USER");
        user.setStatus(0);
        model.addAttribute("user", user);
        model.addAttribute("mode", 0);
        return "user";
    }

    @PostMapping("/admin/author/save")
    public String saveUser(UserDTO user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userService.save(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/author/edit/{id}")
    public String editUser(@PathVariable("id") Integer id, Model model) {
        try {
            UserDTO user = userService.get(id);
            model.addAttribute("user", user);
            model.addAttribute("mode", 1);
        } catch (UserNotFoundException ignored) {

        }
        return "user";
    }

    @GetMapping("/admin/author/del/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        try {
            userService.delete(id);
        } catch (UserNotFoundException ignored) {

        }
        return "redirect:/admin/users";
    }


}