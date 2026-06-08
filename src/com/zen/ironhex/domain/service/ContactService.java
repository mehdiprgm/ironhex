package com.zen.ironhex.domain.service;

import com.zen.ironhex.domain.encryption.ContactEncryptionLayer;
import com.zen.ironhex.domain.entity.main.Bankcard;
import com.zen.ironhex.domain.entity.main.Contact;
import com.zen.ironhex.shared.Result;

import java.sql.SQLException;

public class ContactService {
    private ContactEncryptionLayer encryptionLayer;

    public ContactService() {
        encryptionLayer = new ContactEncryptionLayer();
    }

    public Result insert(Contact contact) throws Exception {
        return encryptionLayer.insert(contact);
    }

    public boolean exists(int userId, String name) throws SQLException {
        return encryptionLayer.exists(userId, name);
    }
}
