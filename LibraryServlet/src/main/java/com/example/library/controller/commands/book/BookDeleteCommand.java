package com.example.library.controller.commands.book;

import com.example.library.controller.commands.Command;
import com.example.library.model.dao.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class BookDeleteCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            DAOFactory.getBook().delete(id);
        } catch (SQLException | ClassNotFoundException | NumberFormatException ex) {
            return "redirect:/admin/books?error";
        }
        return "redirect:/admin/books";
    }
}
