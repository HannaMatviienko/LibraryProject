package com.example.library.controller.commands.publication;

import com.example.library.controller.commands.Command;
import com.example.library.model.dao.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class PublicationsCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            request.setAttribute("publication", DAOFactory.getPublication().getAll());
        } catch (SQLException | ClassNotFoundException ex) {
            new ServletException(ex);
        }
        return "/WEB-INF/jsp/admin/publications.jsp";
    }
}