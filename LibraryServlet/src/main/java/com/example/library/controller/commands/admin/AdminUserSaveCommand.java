package com.example.library.controller.commands.admin;

import com.example.library.controller.commands.Command;
import com.example.library.model.dao.DAOFactory;
import com.example.library.model.entity.User;
import com.example.library.tools.PBKDF2Hasher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class AdminUserSaveCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            User user = new User();
            user.setId(Integer.parseInt(request.getParameter("id")));
            user.setEmail(request.getParameter("email"));
            user.setFirstName(request.getParameter("firstName"));
            user.setLastName(request.getParameter("lastName"));
            user.setPassword(new PBKDF2Hasher().hash(request.getParameter("password").toCharArray()));
            user.setRole(User.ROLE.parseRole(request.getParameter("role")));
            user.setStatus(request.getParameter("status"));
            DAOFactory.getUser().save(user);
        } catch (SQLException | ClassNotFoundException | NumberFormatException ex) {
            throw new ServletException(ex);
        }
        return "redirect:/admin/users";
    }
}
