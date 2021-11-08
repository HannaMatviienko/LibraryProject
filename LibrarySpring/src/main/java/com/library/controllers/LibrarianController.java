package com.library.controllers;

import com.library.dto.AuthorDTO;
import com.library.dto.UserBookDTO;
import com.library.dto.UserDTO;
import com.library.services.UserBookService;
import com.library.services.UserService;
import com.library.tools.AuthorNotFoundException;
import com.library.tools.BookNotFoundException;
import com.library.tools.UserBookNotFoundException;
import com.library.tools.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class LibrarianController {

    private final UserBookService service;
    private final UserService userService;

    @Autowired
    public LibrarianController(UserBookService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @GetMapping("/librarian")
    public String getIndex(Model model) {
        model.addAttribute("card", service.listLibrarian());
        return "librarian";
    }

    @GetMapping("/librarian/users")
    public String getAvailUsers(Model model) {
        model.addAttribute("users", userService.listUsers());
        return "librarian_users";
    }

    @GetMapping("/librarian/card/{id}")
    public String getLib(@PathVariable("id") Integer id, Model model) {
        try {
            UserDTO user = userService.get(id);
            model.addAttribute("user", user);
            model.addAttribute("card", service.listCard(user));
            model.addAttribute("fine", service.listFine(user));
        }
        catch (UserNotFoundException ignored){

        }
        return "librarian_card";
    }

    @GetMapping("/librarian/confirm/card/{location}/{id}")
    public String confirmCard(@PathVariable("location") int location, @PathVariable("id") Integer id) {
        UserBookDTO dto = service.findById(id);
        if (dto != null)
        {
            dto.setLocation(location);
            dto.setStatus(1);
            service.save(dto);
        }
        return "redirect:/librarian";
    }
    @GetMapping("/librarian/cancel/card/{id}")
    public String cancelCard(@PathVariable("id") Integer id) {
        try {
            service.delete(id);
        } catch (UserBookNotFoundException ignored) {
        }
        return "redirect:/librarian";
    }
}