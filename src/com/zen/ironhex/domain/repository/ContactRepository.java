package com.zen.ironhex.domain.repository;

import com.zen.ironhex.domain.database.Database;
import com.zen.ironhex.domain.entity.main.Contact;
import com.zen.ironhex.domain.repository.interfaces.EntityRepository;
import com.zen.ironhex.shared.Result;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ContactRepository implements EntityRepository<Contact> {
    private Database database;

    public ContactRepository() {
        database = new Database();
    }

    @Override
    public Result insert(Contact entity) throws SQLException {
        String sql = "INSERT INTO Contacts (userId,name,phoneNumber,extraInformation,createDate) " +
                "VALUES (?,?,?,?,?)";

        try (Connection connection = database.connect();
             PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setInt(1, entity.getUserId());
            pst.setString(2, entity.getName());

            pst.setString(3, entity.getPhoneNumber());
            pst.setString(4, entity.getExtraInformation());
            pst.setString(5, entity.getCreateDate());

            if (pst.executeUpdate() == 0) {
                return new Result(false, "failed to create new contact");
            }

            return new Result(true, "contact created successfully");
        }
    }

    @Override
    public Result update(Contact entity) throws SQLException {
        return null;
    }

    @Override
    public Result delete(int userId, String name) throws SQLException {
        return null;
    }

    @Override
    public Optional<Contact> select(int userId, String name) throws SQLException {
        return Optional.empty();
    }

    @Override
    public List<Contact> selectAll(int userId) throws SQLException {
        return List.of();
    }

    @Override
    public int count(int userId) throws SQLException {
        return 0;
    }

    @Override
    public boolean exists(int userId, String name) throws SQLException {
        String sql = "SELECT 1 FROM Contacts WHERE userId = ? AND name = ? LIMIT 1";

        try (Connection connection = database.connect();
             PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setInt(1, userId);
            pst.setString(2, name);

            ResultSet rs = pst.executeQuery();
            boolean found = rs.next();

            rs.close();
            return found;
        }
    }
}
