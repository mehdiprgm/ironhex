package com.zen.ironhex.domain.encryption;

import com.zen.ironhex.domain.entity.User;
import com.zen.ironhex.domain.repository.UserRepository;
import com.zen.ironhex.shared.Result;
import com.zen.ironhex.shared.security.VaultProvider;
import com.zen.lib.securityx.key.generator.KeyGenerationResult;
import com.zen.lib.securityx.key.generator.KeyGenerator;
import com.zen.lib.securityx.vault.FastVault;


import java.sql.SQLException;
import java.util.Optional;

public class UserEncryptionLayer {
    private UserRepository repository;

    public UserEncryptionLayer() {
        this.repository = new UserRepository();
    }

    public Result insert(User user) throws Exception {
        KeyGenerationResult generationResult = KeyGenerator.generate(user.getPassword());
        FastVault vault = new VaultProvider().makeVault(user.getPassword(), generationResult.salt());

        user.setPassword(vault.encrypt(user.getPassword()));
        user.setSalt(generationResult.salt());

        if (user.getLastLoginDate() != null) {
            user.setLastLoginDate(vault.encrypt(user.getLastLoginDate()));
        }

        return repository.insert(user);
    }

    public Optional<User> select(String username, String password) throws Exception {
        Optional<User> userOptional = repository.select(0, username);
        if (userOptional.isEmpty()) {
            return userOptional;
        }

        User user = userOptional.get();
        FastVault vault = new VaultProvider().makeVault(password, user.getSalt());

        user.setPassword(vault.decrypt(user.getPassword()));
        if (user.getLastLoginDate() != null) {
            user.setLastLoginDate(vault.decrypt(user.getLastLoginDate()));
        }


        return userOptional;
    }

    public boolean exists(int userId, String username) throws SQLException {
        return repository.exists(userId, username);
    }
}
