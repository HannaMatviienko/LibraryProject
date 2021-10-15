package com.example.library.controller.commands.publication;

import com.example.library.controller.commands.Command;
import com.example.library.model.dao.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class PublicationDeleteCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            DAOFactory.getPublication().delete(id);
        } catch (SQLException | ClassNotFoundException | NumberFormatException ex) {
            return "redirect:/admin/publications?error";
        }
        return "redirect:/admin/publications";
    }
}
