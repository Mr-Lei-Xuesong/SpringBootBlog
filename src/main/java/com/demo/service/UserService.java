package com.demo.service;

import com.demo.bean.User;
import com.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public User login(User user) {
        User login = userMapper.login(user);
        return login;
    }

    @Transactional
    public int saveUser(User user) {
        return userMapper.saveUser(user);
    }

    public User userList(Integer id) {
        return userMapper.userList(id);
    }
}
