package com.gmail.yauhen2012.repository;

import java.sql.Connection;
import java.sql.SQLException;

import com.gmail.yauhen2012.repository.model.User;

public interface UserRepository extends GenericRepository<User> {

    User findUserByName(String username, Connection connection) throws SQLException;

}

