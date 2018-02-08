package com.onionch.webapp.website.service.impl;

import com.onionch.webapp.website.bean.User;
import com.onionch.webapp.website.mapper.UserMapper;
import com.onionch.webapp.website.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> listAll(){
        return userMapper.listAll();
    }

    @Override
    public void create(User user) {
        userMapper.create(user);
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public void delete(String uId) {
        userMapper.delete(uId);
    }

    @Override
    public User selectByName(String userName) {
        return userMapper.selectByName(userName);
    }

    @Override
    public User selectByUid(String uId) {
        return userMapper.selectByUid(uId);
    }
}
