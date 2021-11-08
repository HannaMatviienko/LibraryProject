package com.example.library.model.dao;

import com.example.library.model.entity.Author;
import com.example.library.model.entity.Book;
import com.example.library.model.entity.Page;
import com.example.library.model.entity.Publication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class BookDAO {

    public Book get(int id, Connection connection) throws SQLException,
            ClassNotFoundException {

        boolean isConnection = connection != null;
        if (!isConnection)
            connection = ConnectionPool.getConnection();
        String sql = "SELECT * FROM books WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet result = statement.executeQuery();

        Book book = null;
        if (result.next()) {
            book = new Book();
            book.setId(result.getInt("id"));
            book.setName(result.getString("name"));
            book.setAuthor(DAOFactory.getAuthor().get(result.getInt("author_id")));
            book.setPublication(DAOFactory.getPublication().get(result.getInt("publication_id")));
            book.setYearPublication(result.getInt("year_publication"));
            book.setNumberOf((result.getInt("number_of")));
            book.setAvailable((result.getInt("available")));
        }

        if (!isConnection)
            connection.close();

        return book;
    }

    private String getQuery(Page page, Boolean isUser) {
        String sql = "";
        if (page == null) return sql;

        boolean isFirst = true;
        Hashtable<String, String> query = page.getQuery();
        for (String col : query.keySet()) {
            if (isFirst && !isUser)
                sql = sql + "WHERE ";
            else
                sql = sql + "AND ";
            isFirst = false;

            String val = query.get(col);

            switch (col) {
                case "name":
                case "year_publication":
                    col = "b." + col;
                    break;
                case "author_name":
                    col = "a.name";
                    break;
                case "publication_name":
                    col = "p.name";
                    break;
            }
            sql = sql + col + " LIKE '%" + val + "%' ";
        }
        return sql;
    }

    public List<Book> getAll(Page page, Boolean isUser) throws SQLException,
            ClassNotFoundException {

        Connection connection = ConnectionPool.getConnection();

        String sql;
        PreparedStatement statement;
        ResultSet result;

        if (page != null) {
            sql = "select count(*) " +
                    "from books b " +
                    "   join authors a on a.id = b.author_id " +
                    "   join publications p on p.id = b.publication_id ";
            if (isUser)
                sql = sql + "WHERE b.available > 0 ";
            sql = sql + getQuery(page, isUser);

            System.out.println(sql);

            statement = connection.prepareStatement(sql);
            result = statement.executeQuery();
            if (result.next())
                page.setCount(result.getInt(1));
        }

        sql = "select b.*, " +
                "   a.name as author_name, " +
                "   p.name as publication_name " +
                " from books b " +
                "   join authors a on a.id = b.author_id " +
                "   join publications p on p.id = b.publication_id ";

        if (isUser)
            sql = sql + "WHERE b.available > 0 ";
        if (page != null) {
            sql = sql + getQuery(page, isUser);
            sql = sql + " ORDER BY " + page.getColumn() + " " + page.getDirection();
            sql = sql + " LIMIT " + (page.getPage() - 1) * page.getLimit() + "," + page.getLimit();
        }

        statement = connection.prepareStatement(sql);
        result = statement.executeQuery();
        List<Book> list = new ArrayList<>();

        while (result.next()) {
            Book book = new Book();
            book.setId(result.getInt("id"));
            book.setName(result.getString("name"));
            book.setNumberOf((result.getInt("number_of")));
            book.setAvailable((result.getInt("available")));
            book.setYearPublication(result.getInt("year_publication"));
            book.setAuthor(new Author(
                    result.getInt("author_id"),
                    result.getString("author_name")));
            book.setPublication(new Publication(
                    result.getInt("publication_id"),
                    result.getString("publication_name")));
            list.add(book);
        }

        connection.close();
        return list;
    }

    public List<Book> getUserBooks() throws SQLException,
            ClassNotFoundException {
        Connection connection = ConnectionPool.getConnection();
        String sql = "SELECT * FROM books WHERE available > 0";
        PreparedStatement statement = connection.prepareStatement(sql);

        ResultSet result = statement.executeQuery();
        List<Book> list = new ArrayList<>();

        while (result.next()) {
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
            sql = "INSERT INTO books (name, author_id, publication_id, year_publication, number_of, available) VALUES (?, ?, ?, ?, ?, ?)";
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
        else
            statement.setInt(6, book.getNumberOf());

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
