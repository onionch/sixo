package com.onionch.webapp.website.mapper;

import com.onionch.webapp.website.bean.User;

import java.util.List;

public interface UserMapper {
    List<User> listAll();
    void create(User user);
    void update(User user);
    void delete(String uId);
    User selectByName(String userName);
    User selectByUid(String uId);
}

