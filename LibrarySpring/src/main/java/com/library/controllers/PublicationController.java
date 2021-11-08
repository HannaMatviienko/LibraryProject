package com.library.controllers;

import com.library.dto.PublicationDTO;
import com.library.services.PublicationService;
import com.library.tools.PublicationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PublicationController {

    private final PublicationService service;

    @Autowired
    public PublicationController(PublicationService service) {
        this.service = service;
    }

    @GetMapping("/admin/publications")
    public String getAuthors(Model model) {
        model.addAttribute("publications", service.listAll());
        return "publications";
    }

    @GetMapping("/admin/publication/new")
    public String add(Model model) {
        PublicationDTO dto = new PublicationDTO();
        model.addAttribute("publication", dto);
        model.addAttribute("mode", 0);
        return "publication";
    }

    @PostMapping("/admin/publication/save")
    public String save(PublicationDTO dto) {
        service.save(dto);
        return "redirect:/admin/publications";
    }

    @GetMapping("/admin/publication/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        try {
            PublicationDTO dto = service.get(id);
            model.addAttribute("publication", dto);
            model.addAttribute("mode", 1);
        } catch (PublicationNotFoundException ignored) {

        }
        return "publication";
    }

    @GetMapping("/admin/publication/del/{id}")
    public String delete(@PathVariable("id") Integer id) {
        try {
            service.delete(id);
        } catch (PublicationNotFoundException ignored) {

        }
        return "redirect:/admin/publications";
    }
}