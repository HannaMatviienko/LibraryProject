package com.example.library.controller.commands.user;

import com.example.library.controller.commands.Command;
import com.example.library.model.dao.DAOFactory;
import com.example.library.model.dao.UserDAO;
import com.example.library.model.entity.User;
import com.example.library.model.entity.UserBook;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

public class UserBooksCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            if (user == null)
                throw new ServletException("User not found");

            UserDAO dao = DAOFactory.getUser();
            List<UserBook> books = dao.getBooks(user.getId());
            List<UserBook> fines = dao.getFines(user.getId());
            request.setAttribute("books", books);
            request.setAttribute("fines", fines);
            request.setAttribute("user", user);
        } catch (SQLException | ClassNotFoundException ex) {
            throw new ServletException(ex);
        }
        return "/WEB-INF/jsp/user/user_account.jsp";
    }
}