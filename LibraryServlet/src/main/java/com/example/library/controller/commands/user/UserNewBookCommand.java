package com.example.library.controller.commands.user;

import com.example.library.controller.commands.Command;
import com.example.library.model.dao.DAOFactory;
import com.example.library.model.entity.Page;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class UserNewBookCommand implements Command {
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


        String name = request.getParameter("name");
        if (name != null) {
            request.setAttribute("name", name);
            page.addQuery("name", name);
        }

        String author_name = request.getParameter("author_name");
        if (author_name != null) {
            request.setAttribute("author_name", author_name);
            page.addQuery("author_name", author_name);
        }

        String publication_name = request.getParameter("publication_name");
        if (publication_name != null) {
            request.setAttribute("publication_name", publication_name);
            page.addQuery("publication_name", publication_name);
        }

        String year_publication = request.getParameter("year_publication");
        if (year_publication != null) {
            request.setAttribute("year_publication", year_publication);
            page.addQuery("year_publication", year_publication);
        }

        try {
            request.setAttribute("books", DAOFactory.getBook().getAll(page, true));
            request.setAttribute("scol", page.getColumn());
            request.setAttribute("sdir", page.getDirection());
            request.setAttribute("page", page.getPage());
            request.setAttribute("pageCount", page.getPageCount());
        } catch (SQLException | ClassNotFoundException ex) {
            throw new ServletException(ex);
        }
        return "/WEB-INF/jsp/user/order.jsp";
    }
}
