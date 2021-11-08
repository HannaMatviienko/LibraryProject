package com.library.controllers;

import com.library.dto.BookDTO;
import com.library.dto.UserBookDTO;
import com.library.dto.UserDTO;
import com.library.services.BookService;
import com.library.services.UserBookService;
import com.library.services.UserService;
import com.library.tools.BookNotFoundException;
import com.library.tools.UserBookNotFoundException;
import com.library.tools.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;

@Controller
public class CardController {

    private final UserBookService service;
    private final UserService userService;
    private final BookService bookService;


    @Autowired
    public CardController(UserBookService service, UserService userService, BookService bookService) {
        this.service = service;
        this.userService = userService;
        this.bookService = bookService;
    }

    @GetMapping("/card")
    public String get(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDTO user = userService.findUserByEmail(auth.getName());
        model.addAttribute("user", user);
        model.addAttribute("card", service.listCard(user));
        model.addAttribute("fine", service.listFine(user));
        return "card";
    }

    @GetMapping("/card/pay/{id}")
    public String payFine(@PathVariable("id") Integer id) {
        UserBookDTO userBook = service.findById(id);
        userBook.setStatus(2);
        userBook.setDateActual(new Date());
        service.save(userBook);
        return "redirect:/card";
    }

    @GetMapping("/card/delete/{id}")
    public String deleteOrderedBook(@PathVariable("id") Integer id) {
        try {
            service.delete(id);
        } catch (UserBookNotFoundException ignore) {

        }
        return "redirect:/card";
    }

    @GetMapping("/card/return/{id}")
    public String returnOrderedBook(@PathVariable("id") Integer id) {
        UserBookDTO userBook = service.findById(id);
        userBook.setStatus(2);
        userBook.setDateActual(new Date());
        service.save(userBook);
        return "redirect:/card";
    }

    @GetMapping("/card/order/{id}")
    public String order(@PathVariable("id") Integer id) {
        UserBookDTO userBook = service.findById(id);

        UserBookDTO newOrder = new UserBookDTO();
        newOrder.setBook(userBook.getBook());
        newOrder.setUser(userBook.getUser());
        service.save(newOrder);

        return "redirect:/card";
    }

    @GetMapping("/card/add/{id}")
    public String add(@PathVariable("id") Integer id) {
        try {
            BookDTO book = bookService.get(id);
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            UserDTO user = userService.findUserByEmail(auth.getName());
            UserBookDTO newOrder = new UserBookDTO();
            newOrder.setBook(book);
            newOrder.setUser(user);
            service.save(newOrder);
        } catch (BookNotFoundException ignored) {
        }
        return "redirect:/card";
    }
}