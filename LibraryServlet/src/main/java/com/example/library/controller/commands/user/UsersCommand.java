package com.example.library.controller.commands.user;

import com.example.library.controller.commands.Command;
import com.example.library.model.dao.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class UsersCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            request.setAttribute("users", DAOFactory.getUser().get());
            return "/WEB-INF/jsp/users.jsp";
        } catch (SQLException | ClassNotFoundException ex) {
            throw new ServletException(ex);
        }
    }
}