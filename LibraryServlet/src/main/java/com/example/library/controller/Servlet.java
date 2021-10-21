package com.example.library.controller;

import com.example.library.controller.commands.Command;
import com.example.library.controller.commands.IndexCommand;
import com.example.library.controller.commands.admin.*;
import com.example.library.controller.commands.author.*;
import com.example.library.controller.commands.book.*;
import com.example.library.controller.commands.librarian.*;
import com.example.library.controller.commands.publication.*;
import com.example.library.controller.commands.user.*;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "Servlet", value = "/")
public class Servlet extends HttpServlet {

    private Map<String, Command> commands = new HashMap<>();

    @Override
    public void init(ServletConfig servletConfig) {
        commands = new HashMap<>();
        commands.put("/user/login", new LogInCommand());
        commands.put("/user", new UserBooksCommand());
        commands.put("/user/logout", new LogOutCommand());
        commands.put("/user/signup", new SignUpCommand());
        commands.put("/user/new", new UserNewBookCommand());
        commands.put("/user/order", new UserOrderCommand());
        commands.put("/user/delete", new UserDeleteOrderBookCommand());
        commands.put("/user/return", new UserReturnBookCommand());
        commands.put("/user/pay", new UserPayFineCommand());

        commands.put("/admin", new AdminCommand());
        commands.put("/admin/users", new AdminUsersCommand());
        commands.put("/admin/users/delete", new AdminUserDeleteCommand());
        commands.put("/admin/users/edit", new AdminUserEditCommand());
        commands.put("/admin/users/new", new AdminUserNewCommand());
        commands.put("/admin/users/save", new AdminUserSaveCommand());

        commands.put("/admin/authors", new AuthorsCommand());
        commands.put("/admin/authors/delete", new AuthorDeleteCommand());
        commands.put("/admin/authors/edit", new AuthorEditCommand());
        commands.put("/admin/authors/new", new AuthorNewCommand());
        commands.put("/admin/authors/save", new AuthorSaveCommand());

        commands.put("/admin/books/delete", new BookDeleteCommand());
        commands.put("/admin/books/edit", new BookEditCommand());
        commands.put("/admin/books/new", new BookNewCommand());
        commands.put("/admin/books/save", new BookSaveCommand());
        commands.put("/admin/books", new BooksCommand());

        commands.put("/admin/publications", new PublicationsCommand());
        commands.put("/admin/publications/delete", new PublicationDeleteCommand());
        commands.put("/admin/publications/edit", new PublicationEditCommand());
        commands.put("/admin/publications/new", new PublicationNewCommand());
        commands.put("/admin/publications/save", new PublicationSaveCommand());

        commands.put("/librarian", new LibrarianCommand());
        commands.put("/librarian/approve", new LibrarianApproveOrderCommand());
        commands.put("/librarian/cancel", new LibrarianCancelOrderCommand());
        commands.put("/librarian/ordered", new LibrarianOrderedBooksCommand());
        commands.put("/librarian/users", new LibrarianAccessUsersCommand());
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();
        Command command = commands.getOrDefault(path, new IndexCommand());
        String page = command.execute(request, response);
        if (page.contains("redirect:"))
            response.sendRedirect(request.getContextPath() + page.replace("redirect:", "/"));
        else
            request.getRequestDispatcher(page).forward(request, response);
    }
}