package com.zen.ironhex.shared.security;


import com.zen.lib.securityx.key.KeyDerivationService;
import com.zen.lib.securityx.vault.FastVault;

public class VaultProvider {
    public FastVault makeVault(String password, String salt) {
        KeyDerivationService service = new KeyDerivationService();
        return new FastVault(service.deriveKey(password, salt));
    }
}
