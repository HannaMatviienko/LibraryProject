package com.example.library.controller.commands.admin;

import com.example.library.controller.commands.Command;
import com.example.library.model.dao.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class AdminCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try
        {
            request.setAttribute("users", DAOFactory.getUser().get());
            request.setAttribute("books", DAOFactory.getBook().getAll(null, false));
            request.setAttribute("publication", DAOFactory.getPublication().getAll());
            request.setAttribute("author", DAOFactory.getAuthor().getAll());
        }
        catch (SQLException |ClassNotFoundException ex)
        {
            new ServletException(ex);
        }
        return "/WEB-INF/jsp/admin/admin.jsp";
    }
}