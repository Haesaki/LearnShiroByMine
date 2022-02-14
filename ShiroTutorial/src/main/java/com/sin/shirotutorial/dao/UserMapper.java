package com.sin.shirotutorial.dao;

import com.sin.shirotutorial.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    public User selectUserByName(String name);
}
