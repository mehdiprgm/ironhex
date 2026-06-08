package com.zen.ironhex.domain.repository.interfaces;

import com.zen.ironhex.domain.entity.base.BaseEntity;
import com.zen.ironhex.shared.Result;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface EntityRepository<T extends BaseEntity> {
    Result insert(T entity) throws SQLException;

    Result update(T entity) throws SQLException;

    Result delete(int userId, String name) throws SQLException;

    Optional<T> select(int userId, String name) throws SQLException;

    List<T> selectAll(int userId) throws SQLException;

    int count(int userId) throws SQLException;

    boolean exists(int userId, String name) throws SQLException;
}
