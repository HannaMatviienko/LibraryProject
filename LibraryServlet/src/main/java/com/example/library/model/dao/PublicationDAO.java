package com.example.library.model.dao;

import com.example.library.model.entity.Publication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PublicationDAO {

    public Publication get(int id, Connection connection) throws SQLException,
            ClassNotFoundException {
        boolean isConnection = connection != null;
        if (!isConnection)
            connection = ConnectionPool.getConnection();

        String sql = "SELECT * FROM publications WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet result = statement.executeQuery();

        Publication publication = null;
        if (result.next()) {
            publication = new Publication();
            publication.setId(result.getInt("id"));
            publication.setName(result.getString("name"));
        }
        if (!isConnection)
            connection.close();

        return publication;
    }

    public Publication get(int id) throws SQLException,
            ClassNotFoundException {
        return get(id, null);
    }

    public List<Publication> getAll() throws SQLException,
            ClassNotFoundException {
        Connection connection = ConnectionPool.getConnection();
        String sql = "SELECT * FROM publications";
        PreparedStatement statement = connection.prepareStatement(sql);

        ResultSet result = statement.executeQuery();
        List<Publication> list = new ArrayList<>();

        while (result.next()){
            Publication publication = new Publication();
            publication.setId(result.getInt("id"));
            publication.setName(result.getString("name"));
            list.add(publication);
        }

        connection.close();
        return list;
    }
}
