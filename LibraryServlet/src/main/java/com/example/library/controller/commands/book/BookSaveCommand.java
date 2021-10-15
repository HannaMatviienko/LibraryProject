package com.example.library.controller.commands.book;

import com.example.library.controller.commands.Command;
import com.example.library.model.dao.DAOFactory;
import com.example.library.model.entity.Author;
import com.example.library.model.entity.Book;
import com.example.library.model.entity.Publication;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class BookSaveCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            Book book = new Book();
            book.setId(Integer.parseInt(request.getParameter("id")));
            book.setName(request.getParameter("name"));
            book.setAuthor(new Author(Integer.parseInt(request.getParameter("authorId"))));
            book.setPublication(new Publication(Integer.parseInt(request.getParameter("publicationId"))));
            book.setYearPublication(Integer.parseInt(request.getParameter("yearPublication")));
            book.setNumberOf(Integer.parseInt(request.getParameter("numberOf")));
            DAOFactory.getBook().save(book);
        } catch (SQLException | ClassNotFoundException | NumberFormatException ex) {
            throw new ServletException(ex);
        }
        return "redirect:/admin/books";
    }
}
