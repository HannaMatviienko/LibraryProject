package com.example.library.controller.commands.user;

import com.example.library.controller.commands.Command;
import com.example.library.model.dao.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class UserPayFineCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            DAOFactory.getUser().payFine(id);
        } catch (SQLException | ClassNotFoundException | NumberFormatException ex) {
            return "redirect:/user?error";
        }
        return "redirect:/user";
    }
}