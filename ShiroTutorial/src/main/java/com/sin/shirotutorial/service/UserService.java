package com.sin.shirotutorial.service;

import com.sin.shirotutorial.entity.User;

public interface UserService {
    public User selectByName(String userName);
}
