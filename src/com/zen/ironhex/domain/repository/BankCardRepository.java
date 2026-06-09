package com.zen.ironhex.domain.repository;

import com.zen.ironhex.domain.database.Database;
import com.zen.ironhex.domain.entity.main.Account;
import com.zen.ironhex.domain.entity.main.Bankcard;
import com.zen.ironhex.domain.repository.interfaces.EntityRepository;
import com.zen.ironhex.shared.Result;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BankCardRepository implements EntityRepository<Bankcard> {
    private Database database;

    public BankCardRepository() {
        database = new Database();
    }

    @Override
    public Result insert(Bankcard entity) throws SQLException {
        String sql = "INSERT INTO BankCards (userId,name,cardNumber,accountNumber,cvv2,expireDate,password,createDate) " +
                "VALUES (?,?,?,?,?,?,?,?)";

        try (Connection connection = database.connect();
             PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setInt(1, entity.getUserId());
            pst.setString(2, entity.getName());

            pst.setString(3, entity.getCardNumber());
            pst.setString(4, entity.getAccountNumber());

            pst.setString(5, entity.getCvv2());
            pst.setString(6, entity.getExpireDate());

            pst.setString(7, entity.getPassword());
            pst.setString(8, entity.getCreateDate());

            if (pst.executeUpdate() == 0) {
                return new Result(false, "failed to create bank card");
            }

            return new Result(true, "bank card created successfully");
        }
    }

    @Override
    public Result update(Bankcard entity) throws SQLException {
        return null;
    }

    @Override
    public Result delete(int userId, String name) throws SQLException {
        return null;
    }

    @Override
    public Optional<Bankcard> select(int userId, String name) throws SQLException {
        return Optional.empty();
    }

    @Override
    public List<Bankcard> selectAll(int userId) throws SQLException {
        String sql = "SELECT * FROM BankCards WHERE userId = ?";
        List<Bankcard> bankCards = new ArrayList<>();

        try (Connection connection = database.connect();
             PreparedStatement pst = connection.prepareStatement(sql)) {

            pst.setInt(1, userId);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Bankcard bankcard = new Bankcard(
                        rs.getInt("userId"),
                        rs.getString("name"),
                        rs.getString("cardNumber"),
                        rs.getString("accountNumber"),
                        rs.getString("cvv2"),
                        rs.getString("expireDate"),
                        rs.getString("password"),
                        rs.getString("createDate")
                );

                bankcard.setId(rs.getInt("id"));
                bankCards.add(bankcard);
            }
        }

        return bankCards;
    }

    @Override
    public int count(int userId) throws SQLException {
        return 0;
    }

    @Override
    public boolean exists(int userId, String name) throws SQLException {
        String sql = "SELECT 1 FROM BankCards WHERE userId = ? AND name = ? LIMIT 1";

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
