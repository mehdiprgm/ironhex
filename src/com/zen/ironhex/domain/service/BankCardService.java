package com.zen.ironhex.domain.service;

import com.zen.ironhex.domain.encryption.BankCardEncryptionLayer;
import com.zen.ironhex.domain.entity.main.Account;
import com.zen.ironhex.domain.entity.main.Bankcard;
import com.zen.ironhex.shared.Result;

import java.sql.SQLException;
import java.util.List;

public class BankCardService {
    private BankCardEncryptionLayer encryptionLayer;

    public BankCardService() {
        encryptionLayer = new BankCardEncryptionLayer();
    }

    public Result insert(Bankcard bankcard) throws Exception {
        return encryptionLayer.insert(bankcard);
    }

    public boolean exists(int userId, String name) throws SQLException {
        return encryptionLayer.exists(userId, name);
    }

    public List<Bankcard> selectAll(int userId) throws Exception {
        return encryptionLayer.selectAll(userId);
    }
}
