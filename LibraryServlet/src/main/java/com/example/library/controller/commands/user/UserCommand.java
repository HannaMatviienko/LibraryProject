package com.example.library.controller.commands.user;

import com.example.library.controller.commands.Command;
import com.example.library.model.dao.AccountDAO;
import com.example.library.model.dao.DAOFactory;
import com.example.library.model.entity.AccountItem;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class UserCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try
        {
            AccountDAO dao = DAOFactory.getAccount();
            List<AccountItem> books = dao.get(1);
            request.setAttribute("books", books);
        }
        catch (SQLException|ClassNotFoundException ex)
        {
            new ServletException(ex);
        }
        return "/WEB-INF/jsp/user_account.jsp";
    }
}