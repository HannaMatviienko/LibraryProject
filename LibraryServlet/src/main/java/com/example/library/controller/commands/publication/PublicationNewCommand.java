package com.example.library.controller.commands.publication;

import com.example.library.controller.commands.Command;
import com.example.library.model.entity.Author;
import com.example.library.model.entity.Publication;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PublicationNewCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        request.setAttribute("publication", new Publication());
        request.setAttribute("mode", 0);
        return "/WEB-INF/jsp/admin/publication.jsp";
    }
}
