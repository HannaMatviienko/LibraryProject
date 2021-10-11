package com.example.library.model.dao;

import com.example.library.model.entity.Publication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PublicationDAO {

    public Publication get(int id) throws SQLException,
            ClassNotFoundException {

        Connection connection = ConnectionPool.getConnection();
        String sql = "SELECT * FROM publications WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet result = statement.executeQuery();

        if (result.next()){
            Publication publication = new Publication();
            publication.setId(result.getInt("id"));
            publication.setName(result.getString("name"));
            return publication;
        }

        return null;
    }
}
