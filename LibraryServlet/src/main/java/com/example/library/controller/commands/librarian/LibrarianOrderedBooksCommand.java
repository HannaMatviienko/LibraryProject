package com.example.library.controller.commands.librarian;

import com.example.library.controller.commands.Command;
import com.example.library.model.dao.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class LibrarianOrderedBooksCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            request.setAttribute("orderedBooks", DAOFactory.getUser().getBooksStatus(0));
        } catch (SQLException | ClassNotFoundException ex) {
            throw new ServletException(ex);
        }
        return "/WEB-INF/jsp/orders.jsp";
    }
}