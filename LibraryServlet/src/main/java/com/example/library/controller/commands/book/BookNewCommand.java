package com.example.library.controller.commands.book;

import com.example.library.controller.commands.Command;
import com.example.library.model.dao.DAOFactory;
import com.example.library.model.entity.Author;
import com.example.library.model.entity.Book;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class BookNewCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            request.setAttribute("book", new Book());
            request.setAttribute("authors", DAOFactory.getAuthor().getAll());
            request.setAttribute("publications", DAOFactory.getPublication().getAll());
            request.setAttribute("mode", 0);
        } catch (SQLException | ClassNotFoundException ex) {
            throw new ServletException(ex);
        }
        return "/WEB-INF/jsp/admin/book.jsp";
    }
}
