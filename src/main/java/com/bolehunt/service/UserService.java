package com.bolehunt.service;

import com.bolehunt.constant.RoleName;
import com.bolehunt.domain.Role;
import com.bolehunt.domain.User;
import com.bolehunt.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User findUserById(final Integer userId) {
        return userMapper.findByUserId(userId);
    }

    public List<User> findAllUsers() {
        return userMapper.findAllUsers();
    }

    public User findUserByUsername(final String username) {
        return userMapper.findByUsername(username);
    }

    @Transactional
    public void insertUser(final User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        userMapper.insertUser(user);
        Role role = userMapper.findRoleByRoleName(RoleName.ROLE_USER.toString());
        userMapper.insertUserRole(user.getUserId(), role.getRoleId());
        LOGGER.info("Register user successfully, userId = [{}], username = [{}], enabled = [{}]", user.getUserId(), user.getUsername(), user.isEnabled());

    }

}
