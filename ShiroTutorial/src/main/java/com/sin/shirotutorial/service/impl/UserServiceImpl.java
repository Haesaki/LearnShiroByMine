package com.sin.shirotutorial.service.impl;

import com.sin.shirotutorial.dao.UserMapper;
import com.sin.shirotutorial.entity.User;
import com.sin.shirotutorial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectByName(String userName) {
        return userMapper.selectUserByName(userName);
    }
}