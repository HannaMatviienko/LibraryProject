package com.library.controllers;

import com.library.dto.BookDTO;
import com.library.dto.BookPage;
import com.library.services.BookService;
import com.library.tools.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookController {

    private final BookService service;

    @Autowired
    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping("/admin/books")
    public String get(Model model,
                      @RequestParam(value = "s", required = false) String s,
                      @RequestParam(value = "d", required = false) String d,
                      @RequestParam(value = "p", required = false) String p,
                      @RequestParam(value = "name", required = false) String name,
                      @RequestParam(value = "author", required = false) String author,
                      @RequestParam(value = "publication", required = false) String publication,
                      @RequestParam(value = "year", required = false) String year) {


        BookPage page = new BookPage();

        if (s != null)
            page.setSortCol(s);
        if (d != null)
            page.setSortDir(d);
        if (p != null)
            page.setPage(Integer.parseInt(p));

        if (name != null)
            page.setQueryName(name);
        if (author != null)
            page.setQueryAuthor(author);
        if (publication != null)
            page.setQueryPublication(publication);
        if (year != null)
            page.setQueryYear(year);

        model.addAttribute("books", service.listAll(page));
        model.addAttribute("page", page);

        model.addAttribute("name_dir", getDir("name", page));
        model.addAttribute("author_dir", getDir("author.name", page));
        model.addAttribute("publication_dir", getDir("publication.name", page));
        model.addAttribute("year_dir", getDir("yearPublication", page));

        model.addAttribute("name_class", getClass("name", page));
        model.addAttribute("author_class", getClass("author.name", page));
        model.addAttribute("publication_class", getClass("publication.name", page));
        model.addAttribute("year_class", getClass("yearPublication", page));

        return "books";
    }

    @GetMapping("/card/order")
    public String getOrder(Model model,
                      @RequestParam(value = "s", required = false) String s,
                      @RequestParam(value = "d", required = false) String d,
                      @RequestParam(value = "p", required = false) String p,
                      @RequestParam(value = "name", required = false) String name,
                      @RequestParam(value = "author", required = false) String author,
                      @RequestParam(value = "publication", required = false) String publication,
                      @RequestParam(value = "year", required = false) String year) {


        BookPage page = new BookPage();

        if (s != null)
            page.setSortCol(s);
        if (d != null)
            page.setSortDir(d);
        if (p != null)
            page.setPage(Integer.parseInt(p));

        if (name != null)
            page.setQueryName(name);
        if (author != null)
            page.setQueryAuthor(author);
        if (publication != null)
            page.setQueryPublication(publication);
        if (year != null)
            page.setQueryYear(year);

        model.addAttribute("books", service.listAllAvailable(page));
        model.addAttribute("page", page);

        model.addAttribute("name_dir", getDir("name", page));
        model.addAttribute("author_dir", getDir("author.name", page));
        model.addAttribute("publication_dir", getDir("publication.name", page));
        model.addAttribute("year_dir", getDir("yearPublication", page));

        model.addAttribute("name_class", getClass("name", page));
        model.addAttribute("author_class", getClass("author.name", page));
        model.addAttribute("publication_class", getClass("publication.name", page));
        model.addAttribute("year_class", getClass("yearPublication", page));

        return "order";
    }


    private String getDir(String col, BookPage page) {
        return page.getSortCol().equals(col) ? page.getSortDir() : "asc";
    }

    private String getClass(String col, BookPage page) {
        return "fa fa-fw " + (page.getSortCol().equals(col) ? (page.getSortDir().equals("asc") ? "fa-sort-up" : "fa-sort-down") : "fa-sort sort_gray");
    }

    @GetMapping("/admin/book/new")
    public String add(Model model) {
        BookDTO dto = new BookDTO();
        model.addAttribute("book", dto);
        model.addAttribute("mode", 0);
        return "book";
    }

    @PostMapping("/admin/book/save")
    public String save(BookDTO dto) {
        service.save(dto);
        return "redirect:/admin/books";
    }

    @GetMapping("/admin/book/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        try {
            BookDTO dto = service.get(id);
            model.addAttribute("book", dto);
            model.addAttribute("mode", 1);
        } catch (BookNotFoundException ignored) {

        }
        return "book";
    }

    @GetMapping("/admin/book/del/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        try {
            service.delete(id);
        } catch (BookNotFoundException ignored) {

        }
        return "redirect:/admin/books";
    }
}