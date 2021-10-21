package com.example.library.controller.commands.librarian;

import com.example.library.controller.commands.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LibrarianCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        return "/WEB-INF/jsp/librarian/librarian.jsp";
    }
}