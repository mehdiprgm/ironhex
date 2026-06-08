package com.zen.ironhex.domain.encryption;

import com.zen.ironhex.domain.entity.main.Bankcard;
import com.zen.ironhex.domain.repository.BankCardRepository;
import com.zen.ironhex.shared.Result;
import com.zen.ironhex.shared.security.VaultProvider;
import com.zen.lib.securityx.vault.FastVault;

import java.sql.SQLException;

import static com.zen.ironhex.shared.Variables.*;

public class BankCardEncryptionLayer {
    private BankCardRepository repository;

    private String password;
    private String salt;

    public BankCardEncryptionLayer() {
        repository = new BankCardRepository();

        this.password = vars.get("password");
        this.salt = vars.get("salt");
    }

    public Result insert(Bankcard bankcard) throws Exception {
        FastVault vault = new VaultProvider().makeVault(password, salt);

        bankcard.setCardNumber(vault.encrypt(bankcard.getCardNumber()));

        if (bankcard.getAccountNumber() != null) {
            bankcard.setAccountNumber(vault.encrypt(bankcard.getAccountNumber()));
        }

        bankcard.setCvv2(vault.encrypt(bankcard.getCvv2()));
        bankcard.setExpireDate(vault.encrypt(bankcard.getExpireDate()));

        bankcard.setPassword(vault.encrypt(bankcard.getPassword()));

        return repository.insert(bankcard);
    }

    public boolean exists(int userId, String name) throws SQLException {
        return repository.exists(userId, name);
    }
}
