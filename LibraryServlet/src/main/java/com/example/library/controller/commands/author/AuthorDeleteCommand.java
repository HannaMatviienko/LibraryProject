package com.example.library.controller.commands.author;

import com.example.library.controller.commands.Command;
import com.example.library.model.dao.DAOFactory;
import com.example.library.model.entity.Author;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class AuthorDeleteCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            DAOFactory.getAuthor().delete(id);
        } catch (SQLException | ClassNotFoundException | NumberFormatException ex) {
            return "redirect:/admin/authors?error";
        }
        return "redirect:/admin/authors";
    }
}
