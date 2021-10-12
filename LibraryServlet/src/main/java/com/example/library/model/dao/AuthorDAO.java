package com.example.library.model.dao;

import com.example.library.model.entity.Author;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDAO {

    public Author get(int id, Connection connection) throws SQLException,
            ClassNotFoundException {
        boolean isConnection = connection != null;
        if (!isConnection)
            connection = ConnectionPool.getConnection();
        String sql = "SELECT * FROM authors WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet result = statement.executeQuery();

        Author author = null;
        if (result.next()) {
            author = new Author();
            author.setId(result.getInt("id"));
            author.setName(result.getString("name"));
        }

        if (!isConnection)
            connection.close();

        return author;
    }

    public Author get(int id) throws SQLException,
            ClassNotFoundException {
        return get(id, null);
    }

    public List<Author> getAll() throws SQLException,
            ClassNotFoundException {
        Connection connection = ConnectionPool.getConnection();
        String sql = "SELECT * FROM authors";
        PreparedStatement statement = connection.prepareStatement(sql);

        ResultSet result = statement.executeQuery();
        List<Author> list = new ArrayList<>();

        while (result.next()){
            Author author = new Author();
            author.setId(result.getInt("id"));
            author.setName(result.getString("name"));
            list.add(author);
        }

        connection.close();
        return list;
    }
}
