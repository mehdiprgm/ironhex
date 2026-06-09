package com.zen.ironhex.domain.repository;

import com.zen.ironhex.domain.database.Database;
import com.zen.ironhex.domain.entity.main.Account;
import com.zen.ironhex.domain.repository.interfaces.EntityRepository;
import com.zen.ironhex.shared.Result;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountRepository implements EntityRepository<Account> {
    private Database database;

    public AccountRepository() {
        this.database = new Database();
    }

    @Override
    public Result insert(Account entity) throws SQLException {
        String sql = "INSERT INTO Accounts (userId,name,username,password,extraInformation,createDate) " +
                "VALUES (?,?,?,?,?,?)";

        try (Connection connection = database.connect();
             PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setInt(1, entity.getUserId());
            pst.setString(2, entity.getName());

            pst.setString(3, entity.getUsername());
            pst.setString(4, entity.getPassword());

            pst.setString(5, entity.getExtraInformation());
            pst.setString(6, entity.getCreateDate());

            if (pst.executeUpdate() == 0) {
                return new Result(false, "Failed to create new account");
            }

            return new Result(true, "account created successfully");
        }
    }

    @Override
    public Result update(Account entity) throws SQLException {
        return null;
    }

    @Override
    public Result delete(int userId, String name) throws SQLException {
        return null;
    }

    @Override
    public Optional<Account> select(int userId, String name) throws SQLException {
        return Optional.empty();
    }

    @Override
    public List<Account> selectAll(int userId) throws SQLException {
        String sql = "SELECT * FROM Accounts WHERE userId = ?";
        List<Account> accounts = new ArrayList<>();

        try (Connection connection = database.connect();
             PreparedStatement pst = connection.prepareStatement(sql)) {

            pst.setInt(1, userId);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Account account = new Account(
                        rs.getInt("userId"),
                        rs.getString("name"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("extraInformation"),
                        rs.getString("createDate")
                );

                account.setId(rs.getInt("id"));
                accounts.add(account);
            }
        }

        return accounts;
    }

    @Override
    public int count(int userId) throws SQLException {
        return 0;
    }

    @Override
    public boolean exists(int userId, String name) throws SQLException {
        String sql = "SELECT 1 FROM Accounts WHERE userId = ? AND name = ? LIMIT 1";

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
