package com.zen.ironhex.domain.encryption;

import com.zen.ironhex.domain.entity.main.Contact;
import com.zen.ironhex.domain.repository.ContactRepository;
import com.zen.ironhex.shared.Result;
import com.zen.ironhex.shared.security.VaultProvider;
import com.zen.lib.securityx.vault.FastVault;

import java.sql.SQLException;

import static com.zen.ironhex.shared.Variables.*;

public class ContactEncryptionLayer {
    private ContactRepository repository;

    private String password;
    private String salt;

    public ContactEncryptionLayer() {
        this.repository = new ContactRepository();

        this.password = vars.get("password");
        this.salt = vars.get("salt");
    }

    public Result insert(Contact contact) throws Exception {
        FastVault vault = new VaultProvider().makeVault(password, salt);

        contact.setPhoneNumber(vault.encrypt(contact.getPhoneNumber()));

        if (contact.getExtraInformation() != null) {
            contact.setExtraInformation(vault.encrypt(contact.getExtraInformation()));
        }

        return repository.insert(contact);
    }

    public boolean exists(int userId, String name) throws SQLException {
        return repository.exists(userId, name);
    }
}
