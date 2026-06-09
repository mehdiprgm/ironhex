package com.zen.ironhex.domain.repository;

import com.zen.ironhex.domain.database.Database;
import com.zen.ironhex.domain.entity.main.Note;
import com.zen.ironhex.domain.entity.main.Password;
import com.zen.ironhex.domain.repository.interfaces.EntityRepository;
import com.zen.ironhex.shared.Result;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PasswordRepository implements EntityRepository<Password> {
    private Database database;

    public PasswordRepository() {
        database = new Database();
    }

    @Override
    public Result insert(Password entity) throws SQLException {
        String sql = "INSERT INTO Passwords (userId,name,password,length,strength,createDate) " +
                "VALUES (?,?,?,?,?,?)";

        try (Connection connection = database.connect();
             PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setInt(1, entity.getUserId());
            pst.setString(2, entity.getName());

            pst.setString(3, entity.getPassword());
            pst.setString(4, entity.getLength());

            pst.setInt(5, entity.getStrength());
            pst.setString(6, entity.getCreateDate());

            if (pst.executeUpdate() == 0) {
                return new Result(false, "Failed to create new password");
            }

            return new Result(true, "password created successfully");
        }
    }

    @Override
    public Result update(Password entity) throws SQLException {
        return null;
    }

    @Override
    public Result delete(int userId, String name) throws SQLException {
        return null;
    }

    @Override
    public Optional<Password> select(int userId, String name) throws SQLException {
        return Optional.empty();
    }

    @Override
    public List<Password> selectAll(int userId) throws SQLException {
        String sql = "SELECT * FROM Passwords WHERE userId = ?";
        List<Password> passwords = new ArrayList<>();

        try (Connection connection = database.connect();
             PreparedStatement pst = connection.prepareStatement(sql)) {

            pst.setInt(1, userId);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Password password = new Password(
                  rs.getInt("userId"),
                  rs.getString("name"),
                  rs.getString("password"),
                  rs.getString("length"),
                  rs.getInt("strength"),
                  rs.getString("createDate")
                );


                password.setId(rs.getInt("id"));
                passwords.add(password);
            }
        }

        return passwords;
    }

    @Override
    public int count(int userId) throws SQLException {
        return 0;
    }

    @Override
    public boolean exists(int userId, String name) throws SQLException {
        String sql = "SELECT 1 FROM Passwords WHERE userId = ? AND name = ? LIMIT 1";

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
