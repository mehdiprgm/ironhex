package com.zen.ironhex.domain.repository;

import com.zen.ironhex.domain.database.Database;
import com.zen.ironhex.domain.entity.User;
import com.zen.ironhex.domain.entity.base.BaseEntity;
import com.zen.ironhex.domain.repository.interfaces.EntityRepository;
import com.zen.ironhex.shared.Result;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserRepository implements EntityRepository<User> {
    private Database database;

    public UserRepository() {
        this.database = new Database();
    }

    @Override
    public Result insert(User entity) throws SQLException {
        String sql = "INSERT INTO Users (username,password,salt,isAdmin,lastLoginDate,createDate) " +
                "VALUES (?,?,?,?,?,?)";

        try (Connection connection = database.connect();
             PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setString(1, entity.getUsername());
            pst.setString(2, entity.getPassword());

            pst.setString(3, entity.getSalt());
            pst.setInt(4, entity.isAdmin() ? 1 : 0);

            pst.setString(5, entity.getLastLoginDate());
            pst.setString(6, entity.getCreateDate());

            if (pst.executeUpdate() == 0) {
                return new Result(false, "failed to create new user");
            }

            return new Result(true, "user created successfully");
        }
    }

    @Override
    public Result update(User entity) throws SQLException {
        return null;
    }

    @Override
    public Result delete(int userId, String name) throws SQLException {
        return null;
    }

    @Override
    public Optional<User> select(int userId, String name) throws SQLException {
        String sql = "SELECT * FROM users WHERE username=?";

        try (Connection connection = database.connect();
             PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setString(1, name);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                User user = new User(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("salt"),
                        rs.getInt("isAdmin") != 0,
                        rs.getString("lastLoginDate"),
                        rs.getString("createDate")
                );

                user.setId(rs.getInt("id"));
                return Optional.of(user);
            }

            return Optional.empty();
        }
    }

    @Override
    public List<User> selectAll(int userId) throws SQLException {
        return List.of();
    }

    @Override
    public int count(int userId) throws SQLException {
        return 0;
    }

    @Override
    public boolean exists(int userId, String name) throws SQLException {
        String sql = "SELECT 1 FROM Users WHERE username = ? LIMIT 1";

        try (Connection connection = database.connect();
             PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setString(1, name);

            ResultSet rs = pst.executeQuery();
            boolean found = rs.next();

            rs.close();
            return found;
        }
    }
}
