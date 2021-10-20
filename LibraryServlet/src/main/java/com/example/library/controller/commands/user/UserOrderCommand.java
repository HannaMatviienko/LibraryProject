package com.example.library.controller.commands.user;

import com.example.library.controller.commands.Command;
import com.example.library.model.dao.DAOFactory;
import com.example.library.model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class UserOrderCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            if (user == null)
                throw new ServletException("User not found");
            int bookId = Integer.parseInt(request.getParameter("id"));
            DAOFactory.getUser().orderBook(bookId, user.getId());
        } catch (SQLException | ClassNotFoundException | NumberFormatException ex) {
            return "redirect:/user?error";
        }
        return "redirect:/user";
    }
}