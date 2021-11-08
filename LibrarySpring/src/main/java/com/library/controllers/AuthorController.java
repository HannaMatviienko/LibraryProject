package com.library.controllers;

import com.library.dto.AuthorDTO;
import com.library.services.AuthorService;
import com.library.tools.AuthorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthorController {

    private final AuthorService service;

    @Autowired
    public AuthorController(AuthorService service) {
        this.service = service;
    }

    @GetMapping("/admin/authors")
    public String getAuthors(Model model) {
        model.addAttribute("authors", service.listAll());
        return "authors";
    }

    @GetMapping("/admin/author/new")
    public String add(Model model) {
        AuthorDTO dto = new AuthorDTO();
        model.addAttribute("author", dto);
        model.addAttribute("mode", 0);
        return "author";
    }

    @PostMapping("/admin/author/save")
    public String save(AuthorDTO dto) {
        service.save(dto);
        return "redirect:/admin/authors";
    }

    @GetMapping("/admin/author/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        try {
            AuthorDTO dto = service.get(id);
            model.addAttribute("author", dto);
            model.addAttribute("mode", 1);
        } catch (AuthorNotFoundException ignored) {

        }
        return "author";
    }

    @GetMapping("/admin/author/del/{id}")
    public String delete(@PathVariable("id") Integer id) {
        try {
            service.delete(id);
        } catch (AuthorNotFoundException ignored) {

        }
        return "redirect:/admin/authors";
    }
}