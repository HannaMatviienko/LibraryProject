package com.example.library.controller.commands.admin;

import com.example.library.controller.commands.Command;
import com.example.library.model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminUserNewCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        request.setAttribute("user", new User());
        request.setAttribute("mode", 0);
        return "/WEB-INF/jsp/admin/user.jsp";
    }
}
