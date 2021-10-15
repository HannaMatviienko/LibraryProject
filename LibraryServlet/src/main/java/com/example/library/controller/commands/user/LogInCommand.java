package com.example.library.controller.commands.user;

import com.example.library.controller.commands.Command;
import com.example.library.model.dao.DAOFactory;
import com.example.library.model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class LogInCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        if (request.getMethod().equals("POST")) {
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            try {
                User user = DAOFactory.getUser().checkLogin(email, password);
                if (user != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);
                    request.setAttribute("username", user.getEmail());

                    if (user.getRole() == User.ROLE.ADMIN)
                        return "redirect:/admin";
                    else if (user.getRole() == User.ROLE.LIBRARIAN)
                        return "redirect:/librarian";
                    else return "redirect:/user";
                } else {
                    request.setAttribute("error", true);
                    request.setAttribute("email", email);
                    request.setAttribute("password", password);
                    return "/WEB-INF/jsp/login.jsp";
                }
            } catch (SQLException | ClassNotFoundException ex) {
                throw new ServletException(ex);
            }
        }
        return "/WEB-INF/jsp/login.jsp";
    }
}