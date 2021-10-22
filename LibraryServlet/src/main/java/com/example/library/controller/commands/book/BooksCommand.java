package com.example.library.controller.commands.book;

import com.example.library.controller.commands.Command;
import com.example.library.model.dao.DAOFactory;
import com.example.library.model.entity.Page;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class BooksCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        Page page = new Page();

        String scol = request.getParameter("s");
        if (scol == null)
            page.setColumn("name");
        else
            page.setColumn(scol);

        String sdir = request.getParameter("d");
        if (sdir != null)
            page.setDirection(sdir);

        String p = request.getParameter("p");
        if (p != null)
            page.setPage(Integer.parseInt(p));

        try
        {
            request.setAttribute("books", DAOFactory.getBook().getAll(page));
            request.setAttribute("scol", page.getColumn());
            request.setAttribute("sdir", page.getDirection());
            request.setAttribute("page", page.getPage());
            request.setAttribute("pageCount", page.getPageCount());
        }
        catch (SQLException |ClassNotFoundException ex)
        {
            throw new ServletException(ex);
        }
        return "/WEB-INF/jsp/admin/books.jsp";
    }
}
