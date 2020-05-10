package com.gmail.yauhen2012.service;

import com.gmail.yauhen2012.service.model.UserDTO;

public interface UserService {

    UserDTO loadUserByUsername(String username);

}
