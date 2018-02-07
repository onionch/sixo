package com.onionch.webapp.website.service;

import com.onionch.webapp.website.bean.User;

import java.util.List;

public interface UserService {
    List<User> listAll();
    void create(User user);
    void update(User user);
    void delete(String uId);
    User selectByName(String userName);
}
