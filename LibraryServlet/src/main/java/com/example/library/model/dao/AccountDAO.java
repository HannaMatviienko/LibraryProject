package com.example.library.model.dao;

import com.example.library.model.entity.AccountItem;
import com.example.library.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {
    public List<AccountItem> get(int accountId) throws SQLException,
            ClassNotFoundException {

        Connection connection = ConnectionPool.getConnection();
        String sql = "SELECT * FROM account_books WHERE account_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, accountId);

        ResultSet result = statement.executeQuery();

        List<AccountItem> list = new ArrayList();

        while (result.next()){
            AccountItem item = new AccountItem();
            item.setId(result.getInt("id"));
            item.setBook(DAOFactory.getBook().get(result.getInt("book_id")));
            list.add(item);
        }

        return list;
    }
}
