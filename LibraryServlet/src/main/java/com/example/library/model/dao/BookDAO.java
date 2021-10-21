package com.example.library.model.dao;

import com.example.library.model.entity.Book;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    public Book get(int id, Connection connection) throws SQLException,
            ClassNotFoundException {

        Boolean isConnection = connection == null;
        if (!isConnection)
            connection = ConnectionPool.getConnection();
        String sql = "SELECT * FROM books WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet result = statement.executeQuery();

        Book book = null;
        if (result.next()){
            book = new Book();
            book.setId(result.getInt("id"));
            book.setName(result.getString("name"));
            book.setAuthor(DAOFactory.getAuthor().get(result.getInt("author_id")));
            book.setPublication(DAOFactory.getPublication().get(result.getInt("publication_id")));
            book.setYearPublication(result.getInt("year_publication"));
            book.setNumberOf((result.getInt("number_of")));
        }

        if(!isConnection)
            connection.close();

        return book;
    }

    public List <Book> getAll() throws SQLException,
            ClassNotFoundException {
        Connection connection = ConnectionPool.getConnection();
        String sql = "SELECT * FROM books";
        PreparedStatement statement = connection.prepareStatement(sql);

        ResultSet result = statement.executeQuery();
        List<Book> list = new ArrayList<>();

        while (result.next()){
            Book book = new Book();
            book.setId(result.getInt("id"));
            book.setName(result.getString("name"));
            book.setAuthor(DAOFactory.getAuthor().get(result.getInt("author_id"), connection));
            book.setPublication(DAOFactory.getPublication().get(result.getInt("publication_id"), connection));
            book.setYearPublication(result.getInt("year_publication"));
            book.setNumberOf((result.getInt("number_of")));
            list.add(book);
        }

        connection.close();
        return list;
    }

    public List <Book> getUserBooks() throws SQLException,
            ClassNotFoundException {
        Connection connection = ConnectionPool.getConnection();
        String sql = "SELECT * FROM books WHERE available > 0";
        PreparedStatement statement = connection.prepareStatement(sql);

        ResultSet result = statement.executeQuery();
        List<Book> list = new ArrayList<>();

        while (result.next()){
            Book book = new Book();
            book.setId(result.getInt("id"));
            book.setName(result.getString("name"));
            book.setAuthor(DAOFactory.getAuthor().get(result.getInt("author_id"), connection));
            book.setPublication(DAOFactory.getPublication().get(result.getInt("publication_id"), connection));
            book.setYearPublication(result.getInt("year_publication"));
            book.setNumberOf((result.getInt("number_of")));
            list.add(book);
        }

        connection.close();
        return list;
    }

    public void save(Book book) throws SQLException, ClassNotFoundException {
        Connection connection = ConnectionPool.getConnection();
        String sql;
        if (book.getId() == -1)
            sql = "INSERT INTO books (name, author_id, publication_id, year_publication, number_of) VALUES (?, ?, ?, ?, ?)";
        else
            sql = "UPDATE books SET name = ?, author_id = ?, publication_id = ?, year_publication =?, number_of = ? WHERE id = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, book.getName());
        statement.setInt(2, book.getAuthor().getId());
        statement.setInt(3, book.getPublication().getId());
        statement.setInt(4, book.getYearPublication());
        statement.setInt(5, book.getNumberOf());
        if (book.getId() != -1)
            statement.setInt(6, book.getId());

        int result = statement.executeUpdate();
        if (result == 0) {
            throw new SQLException("Creating book failed, no rows affected.");
        }
        connection.close();
    }

    public void delete(int id) throws SQLException, ClassNotFoundException {
        Connection connection = ConnectionPool.getConnection();
        String sql = "DELETE FROM books WHERE id = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);

        int result = statement.executeUpdate();
        if (result == 0) {
            throw new SQLException("Deleting book failed, no rows affected.");
        }
        connection.close();
    }
}
