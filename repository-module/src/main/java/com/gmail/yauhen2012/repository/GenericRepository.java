package com.gmail.yauhen2012.repository;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.gmail.yauhen2012.repository.model.Item;

public interface GenericRepository<T> {

    Connection getConnection() throws SQLException;

    T add(T t, Connection connection) throws SQLException;

    List<T> findAll(Connection connection) throws SQLException;

    int delete(Connection connection) throws SQLException;

    int update(Integer id, String newStatus, Connection connection) throws SQLException;

    Item findItemById(Integer id, Connection connection) throws SQLException;

}
