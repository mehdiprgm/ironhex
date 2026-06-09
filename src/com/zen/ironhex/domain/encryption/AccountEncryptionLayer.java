package com.zen.ironhex.domain.encryption;

import com.zen.ironhex.domain.entity.main.Account;
import com.zen.ironhex.domain.repository.AccountRepository;
import com.zen.ironhex.domain.service.AccountService;
import com.zen.ironhex.shared.Result;
import com.zen.ironhex.shared.security.VaultProvider;
import com.zen.lib.securityx.vault.FastVault;

import java.sql.SQLException;
import java.util.List;

import static com.zen.ironhex.shared.Variables.*;

public class AccountEncryptionLayer {
    private AccountRepository repository;

    private String password;
    private String salt;

    public AccountEncryptionLayer() {
        this.repository = new AccountRepository();

        this.password = vars.get("password");
        this.salt =  vars.get("salt");
    }

    public Result insert(Account account) throws Exception {
        FastVault vault = new VaultProvider().makeVault(password, salt);

        account.setUsername(vault.encrypt(account.getUsername()));
        account.setPassword(vault.encrypt(account.getPassword()));

        if (account.getExtraInformation() != null) {
            account.setExtraInformation(vault.encrypt(account.getExtraInformation()));
        }

        return repository.insert(account);
    }

    public boolean exists(int userId, String name) throws SQLException {
        return repository.exists(userId, name);
    }

    public List<Account> selectAll(int userId) throws Exception {
        FastVault vault = new VaultProvider().makeVault(password, salt);
        List<Account> accounts = repository.selectAll(userId);

        for (Account account : accounts) {
            account.setUsername(vault.decrypt(account.getUsername()));
            account.setPassword(vault.decrypt(account.getPassword()));

            if (account.getExtraInformation() != null) {
                account.setExtraInformation(vault.encrypt(account.getExtraInformation()));
            }
        }

        return accounts;
    }
}
