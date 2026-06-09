package com.zen.ironhex.domain.repository;

import com.zen.ironhex.domain.database.Database;
import com.zen.ironhex.domain.entity.main.Account;
import com.zen.ironhex.domain.entity.main.Note;
import com.zen.ironhex.domain.repository.interfaces.EntityRepository;
import com.zen.ironhex.shared.Result;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NoteRepository implements EntityRepository<Note> {
    private Database database;

    public NoteRepository() {
        database = new Database();
    }

    @Override
    public Result insert(Note entity) throws SQLException {
        String sql = "INSERT INTO Notes (userId,name,content,createDate) " +
                "VALUES (?,?,?,?)";

        try (Connection connection = database.connect();
             PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setInt(1, entity.getUserId());
            pst.setString(2, entity.getName());

            pst.setString(3, entity.getContent());
            pst.setString(4, entity.getCreateDate());

            if (pst.executeUpdate() == 0) {
                return new Result(false, "Failed to create new contact");
            }

            return new Result(true, "contact created successfully");
        }
    }

    @Override
    public Result update(Note entity) throws SQLException {
        return null;
    }

    @Override
    public Result delete(int userId, String name) throws SQLException {
        return null;
    }

    @Override
    public Optional<Note> select(int userId, String name) throws SQLException {
        return Optional.empty();
    }

    @Override
    public List<Note> selectAll(int userId) throws SQLException {
        String sql = "SELECT * FROM Notes WHERE userId = ?";
        List<Note> notes = new ArrayList<>();

        try (Connection connection = database.connect();
             PreparedStatement pst = connection.prepareStatement(sql)) {

            pst.setInt(1, userId);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Note note = new Note(
                  rs.getInt("userId"),
                  rs.getString("name"),
                  rs.getString("content"),
                  rs.getString("createDate")
                );

                note.setId(rs.getInt("id"));
                notes.add(note);
            }
        }

        return notes;
    }

    @Override
    public int count(int userId) throws SQLException {
        return 0;
    }

    @Override
    public boolean exists(int userId, String name) throws SQLException {
        String sql = "SELECT 1 FROM Notes WHERE userId = ? AND name = ? LIMIT 1";

        try (Connection connection = database.connect();
             PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setInt(1, userId);
            pst.setString(2, name);

            ResultSet rs = pst.executeQuery();
            boolean found = rs.next();

            rs.close();
            return found;
        }
    }
}
