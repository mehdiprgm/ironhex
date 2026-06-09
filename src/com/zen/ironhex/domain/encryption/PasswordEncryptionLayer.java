package com.zen.ironhex.domain.encryption;

import com.zen.ironhex.domain.entity.main.Note;
import com.zen.ironhex.domain.entity.main.Password;
import com.zen.ironhex.domain.repository.PasswordRepository;
import com.zen.ironhex.shared.Result;
import com.zen.ironhex.shared.security.VaultProvider;
import com.zen.lib.securityx.vault.FastVault;

import java.sql.SQLException;
import java.util.List;

import static com.zen.ironhex.shared.Variables.*;

public class PasswordEncryptionLayer {
    private PasswordRepository repository;

    private String password;
    private String salt;

    public PasswordEncryptionLayer() {
        this.repository = new PasswordRepository();

        this.password = vars.get("password");
        this.salt = vars.get("salt");
    }

    public Result insert(Password password) throws Exception {
        FastVault vault = new VaultProvider().makeVault(this.password, salt);

        password.setPassword(vault.encrypt(password.getPassword()));
        password.setLength(vault.encrypt(password.getLength()));

        return repository.insert(password);
    }

    public boolean exists(int userId, String name) throws SQLException {
        return repository.exists(userId, name);
    }

    public List<Password> selectAll(int userId) throws Exception {
        FastVault vault = new VaultProvider().makeVault(password, salt);
        List<Password> passwords = repository.selectAll(userId);

        for (Password password : passwords) {
            password.setPassword(vault.decrypt(password.getPassword()));
            password.setLength(vault.decrypt(password.getLength()));
        }

        return passwords;
    }
}
