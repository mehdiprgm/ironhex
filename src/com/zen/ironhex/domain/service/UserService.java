package com.zen.ironhex.domain.service;

import com.zen.ironhex.domain.encryption.UserEncryptionLayer;
import com.zen.ironhex.domain.entity.User;
import com.zen.ironhex.shared.Result;

import java.sql.SQLException;
import java.util.Optional;

public class UserService {
    private UserEncryptionLayer encryptionLayer;

    public UserService() {
        encryptionLayer = new UserEncryptionLayer();
    }

    public Result insert(User user) throws Exception {
        return encryptionLayer.insert(user);
    }

    public Optional<User> select(String username, String password) throws Exception {
        return encryptionLayer.select(username, password);
    }

    public boolean exists(String username) throws SQLException {
        return encryptionLayer.exists(0, username);
    }

}
