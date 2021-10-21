package com.example.library.controller.commands.author;

import com.example.library.controller.commands.Command;
import com.example.library.model.dao.DAOFactory;
import com.example.library.model.entity.Author;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class AuthorNewCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        request.setAttribute("author", new Author());
        request.setAttribute("mode", 0);
        return "/WEB-INF/jsp/admin/author.jsp";
    }
}
