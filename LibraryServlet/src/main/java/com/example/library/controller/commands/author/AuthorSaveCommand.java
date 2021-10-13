package com.example.library.controller.commands.author;

import com.example.library.controller.commands.Command;
import com.example.library.model.dao.DAOFactory;
import com.example.library.model.entity.Author;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class AuthorSaveCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            Author author = new Author();
            author.setId(Integer.parseInt(request.getParameter("id")));
            author.setName(request.getParameter("name"));
            DAOFactory.getAuthor().save(author);
        } catch (SQLException | ClassNotFoundException | NumberFormatException ex) {
            throw new ServletException(ex);
        }
        return "redirect:/user/account/admin/authors";
    }
}
