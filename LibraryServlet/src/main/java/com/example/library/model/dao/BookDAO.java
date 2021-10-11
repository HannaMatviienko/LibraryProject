package com.example.library.model.dao;

import com.example.library.model.entity.AccountItem;
import com.example.library.model.entity.Book;
import com.example.library.model.entity.Publication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    public Book get(int id) throws SQLException,
            ClassNotFoundException {

        Connection connection = ConnectionPool.getConnection();
        String sql = "SELECT * FROM books WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet result = statement.executeQuery();

        if (result.next()){
            Book book = new Book();
            book.setId(result.getInt("id"));
            book.setName(result.getString("name"));
            book.setAuthor(DAOFactory.getAuthor().get(result.getInt("author_id")));
            book.setPublication(DAOFactory.getPublication().get(result.getInt("publication_id")));
            book.setYearPublication(result.getInt("year_publication"));
            book.setNumberOf((result.getInt("number_of")));
            return book;
        }

        return null;
    }
}
