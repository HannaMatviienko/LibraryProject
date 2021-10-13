package com.example.library.controller.commands.publication;

import com.example.library.controller.commands.Command;
import com.example.library.model.dao.DAOFactory;
import com.example.library.model.entity.Publication;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class PublicationSaveCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            Publication publication = new Publication();
            publication.setId(Integer.parseInt(request.getParameter("id")));
            publication.setName(request.getParameter("name"));
            DAOFactory.getPublication().save(publication);
        } catch (SQLException | ClassNotFoundException | NumberFormatException ex) {
            throw new ServletException(ex);
        }
        return "redirect:/user/account/admin/publications";
    }
}