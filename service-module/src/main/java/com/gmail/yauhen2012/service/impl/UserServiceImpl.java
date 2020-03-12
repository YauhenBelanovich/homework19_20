package com.gmail.yauhen2012.service.impl;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.SQLException;

import com.gmail.yauhen2012.repository.UserRepository;
import com.gmail.yauhen2012.repository.model.User;
import com.gmail.yauhen2012.service.UserService;
import com.gmail.yauhen2012.service.model.RoleEnum;
import com.gmail.yauhen2012.service.model.UserDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private static final String USER = "user";
    private static final String ADMIN = "admin";

    private final UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;}

    @Override
    public UserDTO loadUserByUsername(String username) {
        UserDTO userDTO = null;
        if (username.equals(USER)) {
            userDTO = getUserDTO(USER);
            if (userDTO != null)
                return userDTO;
        }
        if (username.equals(ADMIN)) {
            userDTO = getUserDTO(ADMIN);
            if (userDTO != null)
                return userDTO;
        }
        return null;
    }

    private UserDTO getUserDTO(String username) {
        UserDTO userDTO;
        try (Connection connection = userRepository.getConnection()){
            connection.setAutoCommit(false);
            try {
                User user = userRepository.findUserByName(username, connection);
                userDTO = convertDatabaseObjectToDTO(user);
                connection.commit();
                return userDTO;
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    private UserDTO convertDatabaseObjectToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setRole(RoleEnum.valueOf(user.getRole()));
        return userDTO;
    }

}
