package com.zen.ironhex.domain.encryption;

import com.zen.ironhex.domain.entity.main.Note;
import com.zen.ironhex.domain.repository.NoteRepository;
import com.zen.ironhex.shared.Result;
import com.zen.ironhex.shared.security.VaultProvider;
import com.zen.lib.securityx.vault.FastVault;

import java.sql.SQLException;

import static com.zen.ironhex.shared.Variables.vars;

public class NoteEncryptionLayer {
    private NoteRepository repository;

    private String password;
    private String salt;

    public NoteEncryptionLayer() {
        this.repository = new NoteRepository();

        this.password = vars.get("password");
        this.salt = vars.get("salt");
    }

    public Result insert(Note note) throws Exception {
        FastVault vault = new VaultProvider().makeVault(password, salt);

        if (note.getContent() != null) {
            note.setContent(vault.encrypt(note.getContent()));
        }

        return repository.insert(note);
    }

    public boolean exists(int userId, String name) throws SQLException {
        return repository.exists(userId, name);
    }
}
