package com.gmail.yauhen2012.repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface GenericRepository<T> {

    Connection getConnection() throws SQLException;

    T add(T t, Connection connection) throws SQLException;

    List<T> findAll(Connection connection) throws SQLException;

    void update(Integer id, String newStatus, Connection connection) throws SQLException;

}
