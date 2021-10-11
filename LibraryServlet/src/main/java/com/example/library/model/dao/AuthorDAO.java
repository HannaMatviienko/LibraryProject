package com.example.library.model.dao;

import com.example.library.model.entity.Author;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorDAO {

    public Author get(int id) throws SQLException,
            ClassNotFoundException {

        Connection connection = ConnectionPool.getConnection();
        String sql = "SELECT * FROM authors WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet result = statement.executeQuery();

        if (result.next()){
            Author author = new Author();
            author.setId(result.getInt("id"));
            author.setName(result.getString("name"));
            return author;
        }

        return null;
    }
}
