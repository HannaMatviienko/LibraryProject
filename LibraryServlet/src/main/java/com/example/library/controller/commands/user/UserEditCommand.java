package com.example.library.controller.commands.user;

import com.example.library.controller.commands.Command;
import com.example.library.model.dao.DAOFactory;
import com.example.library.model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class UserEditCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            User user = DAOFactory.getUser().get(id);
            request.setAttribute("user", user);
            request.setAttribute("authors", DAOFactory.getAuthor().getAll());
            request.setAttribute("publications", DAOFactory.getPublication().getAll());
        } catch (SQLException | ClassNotFoundException | NumberFormatException ex) {
            throw new ServletException(ex);
        }
        request.setAttribute("mode", 1);
        return "/WEB-INF/jsp/user.jsp";
    }
}