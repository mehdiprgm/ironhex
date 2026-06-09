package com.zen.ironhex.domain.service;

import com.zen.ironhex.domain.encryption.AccountEncryptionLayer;
import com.zen.ironhex.domain.entity.User;
import com.zen.ironhex.domain.entity.main.Account;
import com.zen.ironhex.shared.Result;

import java.sql.SQLException;
import java.util.List;

public class AccountService {
    private AccountEncryptionLayer encryptionLayer;

    public AccountService() {
        encryptionLayer = new AccountEncryptionLayer();
    }

    public Result insert(Account account) throws Exception {
        return encryptionLayer.insert(account);
    }

    public boolean exists(int userId, String name) throws SQLException {
        return encryptionLayer.exists(userId, name);
    }

    public List<Account> selectAll(int userId) throws Exception {
        return encryptionLayer.selectAll(userId);
    }
}
