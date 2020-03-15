package com.gmail.yauhen2012.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.gmail.yauhen2012.repository.UserRepository;
import com.gmail.yauhen2012.repository.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl extends GenericRepositoryImpl<User> implements UserRepository {

    @Override
    public User findUserByName(String username, Connection connection) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT username, password, role FROM user WHERE username=?"
        )) {
            statement.setString(1, username);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return getUser(rs);
                }
            }
            return null;
        }
    }

    @Override
    public User add(User user, Connection connection) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<User> findAll(Connection connection) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Integer id, String newStatus, Connection connection) throws SQLException {
        throw new UnsupportedOperationException();
    }

    private User getUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setRole(rs.getString("role"));
        return user;
    }

}
