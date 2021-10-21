package com.example.library.model.dao;

import com.example.library.model.entity.*;
import com.example.library.tools.PBKDF2Hasher;
import com.example.library.tools.UserUnavailableException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public User checkLogin(String email, String password) throws SQLException,
            ClassNotFoundException, UserUnavailableException {

        Connection connection = ConnectionPool.getConnection();
        String sql = "SELECT * FROM users WHERE email = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, email);

        ResultSet result = statement.executeQuery();

        User user = null;

        if (result.next()) {

            int status = result.getInt("status");
            if (status == 0)
                throw new UserUnavailableException();

            String token = result.getString("password");
            try {
                if ((new PBKDF2Hasher()).checkPassword(password.toCharArray(), token)) {
                    user = new User();
                    user.setId(result.getInt("id"));
                    user.setLastName(result.getString("last_name"));
                    user.setFirstName(result.getString("first_name"));
                    user.setEmail(email);
                    if ("ROLE_ADMIN".equals(result.getString("role"))) {
                        user.setRole(User.ROLE.ADMIN);
                    } else if ("ROLE_LIBRARIAN".equals(result.getString("role"))) {
                        user.setRole(User.ROLE.LIBRARIAN);
                    } else {
                        user.setRole(User.ROLE.USER);
                    }
                }
            } catch (IllegalArgumentException ignored) {
            }
        }

        connection.close();
        return user;
    }

    public User newUser(String firstName, String lastName, String email, String password, String status) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionPool.getConnection();
        String sql = "INSERT INTO users ( email, first_name, last_name, password, role, status ) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, email);
        statement.setString(2, firstName);
        statement.setString(3, lastName);
        statement.setString(4, (new PBKDF2Hasher()).hash(password.toCharArray()));
        statement.setString(5, "ROLE_USER");
        statement.setString(6, status);

        int result = statement.executeUpdate();
        if (result == 0) {
            throw new SQLException("Creating user failed, no rows affected.");
        }

        User user;
        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                user = new User();
                user.setId(generatedKeys.getInt(1));
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setEmail(email);
                user.setRole(User.ROLE.USER);
            } else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        }

        connection.close();
        return user;
    }

    public List<User> getUsers() throws SQLException {

        Connection connection = ConnectionPool.getConnection();
        String sql = "SELECT * FROM users WHERE role = 'ROLE_USER'";
        PreparedStatement statement = connection.prepareStatement(sql);

        ResultSet result = statement.executeQuery();
        List<User> list = new ArrayList<>();
        while (result.next()) {
            User user = new User();
            user.setId(result.getInt("id"));
            user.setLastName(result.getString("last_name"));
            user.setFirstName(result.getString("first_name"));
            user.setEmail(result.getString("email"));
            list.add(user);
        }
        connection.close();
        return list;
    }

    public List<User> get() throws SQLException,
            ClassNotFoundException {

        Connection connection = ConnectionPool.getConnection();
        String sql = "SELECT * FROM users";
        PreparedStatement statement = connection.prepareStatement(sql);

        ResultSet result = statement.executeQuery();
        List<User> list = new ArrayList<>();
        while (result.next()) {
            User user = new User();
            user.setId(result.getInt("id"));
            user.setLastName(result.getString("last_name"));
            user.setFirstName(result.getString("first_name"));
            user.setEmail(result.getString("email"));
            user.setRole(User.ROLE.parseRole(result.getString("role")));
            user.setStatus(result.getString("status"));
            list.add(user);
        }
        connection.close();
        return list;
    }

    public User get(int id) throws SQLException,
            ClassNotFoundException {
        Connection connection = ConnectionPool.getConnection();
        String sql = "SELECT * FROM users WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet result = statement.executeQuery();
        User user = null;
        if (result.next()) {
            user = new User();
            user.setId(result.getInt("id"));
            user.setLastName(result.getString("last_name"));
            user.setFirstName(result.getString("first_name"));
            user.setEmail(result.getString("email"));
            user.setRole(User.ROLE.parseRole(result.getString("role")));
            user.setStatus(result.getString("status"));
        }
        connection.close();
        return user;
    }

    public List<User> getRole() throws SQLException,
            ClassNotFoundException {

        Connection connection = ConnectionPool.getConnection();
        String sql = "SELECT * FROM users WHERE role = 'ROLE_USER'";
        PreparedStatement statement = connection.prepareStatement(sql);

        ResultSet result = statement.executeQuery();
        List<User> list = new ArrayList<>();
        while (result.next()) {
            User user = new User();
            user.setId(result.getInt("id"));
            user.setLastName(result.getString("last_name"));
            user.setFirstName(result.getString("first_name"));
            user.setEmail(result.getString("email"));
            user.setRole(User.ROLE.parseRole(result.getString("role")));
            user.setStatus(result.getString("status"));
            list.add(user);
        }
        connection.close();
        return list;
    }

    public void save(User user) throws SQLException, ClassNotFoundException {
        Connection connection = ConnectionPool.getConnection();
        String sql;
        if (user.getId() == -1)
            sql = "INSERT INTO users (email, first_name, last_name, password, role, status) VALUES (?, ?, ?, ?, ?, ?)";
        else
            sql = "UPDATE users SET email = ?, first_name = ?, last_name = ?, password = ?, role = ?, status = ? WHERE id = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, user.getEmail());
        statement.setString(2, user.getFirstName());
        statement.setString(3, user.getLastName());
        statement.setString(4, user.getPassword());
        statement.setString(5, user.getRole().toString());
        statement.setString(6, user.getStatus());
        if (user.getId() != -1)
            statement.setLong(7, user.getId());

        int result = statement.executeUpdate();
        if (result == 0) {
            throw new SQLException("Creating user failed, no rows affected.");
        }
        connection.close();
    }

    public void delete(int id) throws SQLException, ClassNotFoundException {
        Connection connection = ConnectionPool.getConnection();
        String sql = "DELETE FROM users WHERE id = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);

        int result = statement.executeUpdate();
        if (result == 0) {
            throw new SQLException("Deleting user failed, no rows affected.");
        }
        connection.close();
    }

    public List<UserBook> getBooks(long userId) throws SQLException,
            ClassNotFoundException {

        Connection connection = ConnectionPool.getConnection();
        String sql = "SELECT * FROM user_books WHERE user_id = ? AND status <> 3";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, userId);

        ResultSet result = statement.executeQuery();

        List<UserBook> list = new ArrayList<>();

        while (result.next()) {
            UserBook item = new UserBook();
            item.setId(result.getInt("id"));
            item.setBook(DAOFactory.getBook().get(result.getInt("book_id"), connection));
            item.setStatus(result.getInt("status"));
            item.setDateTake(result.getDate("date_take"));
            item.setDateBack(result.getDate("date_back"));
            item.setDateActual(result.getDate("date_actual"));
            list.add(item);
        }
        connection.close();
        return list;
    }

    public List<UserBook> getBooksStatus(int status) throws SQLException,
            ClassNotFoundException {

        Connection connection = ConnectionPool.getConnection();
        String sql = "SELECT * FROM user_books WHERE status = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, status);

        ResultSet result = statement.executeQuery();

        List<UserBook> orders = new ArrayList<>();

        while (result.next()) {
            UserBook item = new UserBook();
            item.setId(result.getInt("id"));
            item.setBook(DAOFactory.getBook().get(result.getInt("book_id"), connection));
            item.setStatus(result.getInt("status"));
            orders.add(item);
        }
        connection.close();
        return orders;
    }

    public void orderBook(int bookId, int userId) throws SQLException, ClassNotFoundException {
        Connection connection = ConnectionPool.getConnection();
        String sql = "INSERT INTO user_books (user_id, book_id) VALUES (?, ?)";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, userId);
        statement.setInt(2, bookId);

        int result = statement.executeUpdate();
        if (result == 0) {
            throw new SQLException("Ordering book failed, no rows affected.");
        }
        connection.close();
    }

    public void deleteOrderBook(int id) throws SQLException, ClassNotFoundException {
        Connection connection = ConnectionPool.getConnection();
        String sql = "DELETE FROM user_books WHERE id = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);

        int result = statement.executeUpdate();
        if (result == 0) {
            throw new SQLException("Deleting book failed, no rows affected.");
        }
        connection.close();
    }

    public void confirmOrderedBook(int id) throws SQLException, ClassNotFoundException {
        Connection connection = ConnectionPool.getConnection();
        String sql = "UPDATE user_books SET status = 1, date_take = curdate(), date_back = DATE_ADD(curdate(), INTERVAL 14 DAY) WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);

        int result = statement.executeUpdate();
        if (result == 0) {
            throw new SQLException("Ordering book failed, no rows affected.");
        }

        decreaseAvailable(connection, id);

        connection.close();
    }

    public void decreaseAvailable(Connection connection, int id) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE books SET available = available - 1 WHERE id IN (SELECT book_id from user_books where id = ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);

        int result = statement.executeUpdate();
        if (result == 0) {
            throw new SQLException("Decreasing available books failed, no rows affected.");
        }
        /*
update books set available = available - 1 where id in
(select book_id from user_books where id = 23)
        */
    }

    public void increaseAvailable(Connection connection, int id) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE books SET available = available + 1 WHERE id IN (SELECT book_id from user_books where id = ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);

        int result = statement.executeUpdate();
        if (result == 0) {
            throw new SQLException("Increasing available books failed, no rows affected.");
        }
        /*
update books set available = available + 1 where id in
(select book_id from user_books where id = 23)
        */
    }


    public void returnBook(int id) throws SQLException, ClassNotFoundException {
        Connection connection = ConnectionPool.getConnection();
        String sql = "UPDATE user_books SET status = 2, date_actual = curdate() WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);

        int result = statement.executeUpdate();
        if (result == 0)
            throw new SQLException("Returning book failed, no rows affected.");

        increaseAvailable(connection, id);
        checkFine(connection, id);

        connection.close();
    }

    public void checkFine(Connection connection, int id) throws SQLException {
        String sql = "SELECT DATEDIFF(date_actual, date_back) AS days, user_id, book_id FROM user_books WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            int days = result.getInt("days");
            if (days > 0)
                saveFine(connection, id, days * 12.5);
        }
    }

    public void saveFine(Connection connection, int id, double fine) throws SQLException {
        String sql = "UPDATE user_books SET status = 3, fine = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setDouble(1, fine);
        statement.setInt(2, id);
        int result = statement.executeUpdate();
        if (result == 0) {
            throw new SQLException("Creating fine failed, no rows affected.");
        }
    }

    public List<UserBook> getFines(long userId) throws SQLException,
            ClassNotFoundException {

        Connection connection = ConnectionPool.getConnection();
        String sql =
                "SELECT ub.id, ub.fine, ub.book_id, " +
                "b.name AS book_name, b.year_publication, " +
                "b.publication_id, p.name AS publication_name, " +
                "b.author_id, a.name AS author_name, ub.date_back, " +
                "DATEDIFF(ub.date_actual, ub.date_back) AS days FROM user_books ub " +
                "JOIN books b ON b.id = ub.book_id " +
                "JOIN publications p ON p.id = b.publication_id " +
                "JOIN authors a ON a.id = b.author_id " +
                "WHERE ub.user_id = ? AND ub.status = 3";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, userId);

        ResultSet result = statement.executeQuery();

        List<UserBook> list = new ArrayList<>();

        while (result.next()) {
            UserBook item = new UserBook();
            item.setId(result.getInt("id"));
            item.setFine(result.getDouble("fine"));
            item.setDateBack(result.getDate("date_back"));
            item.setDays(result.getInt("days"));

            Book book = new Book();
            book.setId(result.getInt("book_id"));
            book.setName(result.getString("book_name"));
            book.setYearPublication(result.getInt("year_publication"));

            Author author = new Author();
            author.setId(result.getInt("author_id"));
            author.setName(result.getString("author_name"));

            Publication publication = new Publication();
            publication.setId(result.getInt("publication_id"));
            publication.setName(result.getString("publication_name"));

            book.setAuthor(author);
            book.setPublication(publication);
            item.setBook(book);

            list.add(item);
        }
        connection.close();
        return list;
    }

    public void payFine(int id) throws SQLException, ClassNotFoundException {
        Connection connection = ConnectionPool.getConnection();
        String sql = "UPDATE user_books SET status = 2 WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        int result = statement.executeUpdate();
        if (result == 0) {
            throw new SQLException("Paying fine failed, no rows affected.");
        }
    }
}