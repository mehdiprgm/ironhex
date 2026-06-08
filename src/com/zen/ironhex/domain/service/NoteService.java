package com.zen.ironhex.domain.service;

import com.zen.ironhex.domain.encryption.NoteEncryptionLayer;
import com.zen.ironhex.domain.entity.main.Note;
import com.zen.ironhex.shared.Result;

import java.sql.SQLException;

public class NoteService {
    private NoteEncryptionLayer encryptionLayer;

    public NoteService() {
        encryptionLayer = new NoteEncryptionLayer();
    }

    public Result insert(Note note) throws Exception {
        return encryptionLayer.insert(note);
    }

    public boolean exists(int userId, String name) throws SQLException {
        return encryptionLayer.exists(userId, name);
    }
}
