package com.example.library.controller.commands.publication;

import com.example.library.controller.commands.Command;
import com.example.library.model.dao.DAOFactory;
import com.example.library.model.entity.Publication;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class PublicationEditCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Publication publication = DAOFactory.getPublication().get(id);
            request.setAttribute("publication", publication);
        } catch (SQLException | ClassNotFoundException | NumberFormatException ex) {
            throw new ServletException(ex);
        }
        request.setAttribute("mode", 1);
        return "/WEB-INF/jsp/publication.jsp";
    }
}