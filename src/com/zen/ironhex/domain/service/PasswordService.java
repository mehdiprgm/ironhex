package com.zen.ironhex.domain.service;

import com.zen.ironhex.domain.encryption.PasswordEncryptionLayer;
import com.zen.ironhex.domain.entity.main.Account;
import com.zen.ironhex.domain.entity.main.Password;
import com.zen.ironhex.shared.Result;

import java.sql.SQLException;

public class PasswordService {
    private PasswordEncryptionLayer encryptionLayer;

    public PasswordService() {
        encryptionLayer = new PasswordEncryptionLayer();
    }

    public Result insert(Password password) throws Exception {
        return encryptionLayer.insert(password);
    }

    public boolean exists(int userId, String name) throws SQLException {
        return encryptionLayer.exists(userId, name);
    }
}
