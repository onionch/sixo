package com.onionch.webapp.website.service.impl;

import com.onionch.webapp.website.bean.Token;
import com.onionch.webapp.website.mapper.TokenMapper;
import com.onionch.webapp.website.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tokenServiceImpl")
public class TokenServiceImpl implements TokenService{

    @Autowired
    private TokenMapper tokenMapper;

    @Override
    public void create(Token token) {
        tokenMapper.create(token);
    }

    @Override
    public void updateLastDate() {
        tokenMapper.updateLastDate();
    }

    @Override
    public void delete(String uId) {
        tokenMapper.delete(uId);
    }

    @Override
    public Token selectByUid(String uId) {
        Token token=tokenMapper.selectByUid(uId);
        return token;
    }

    @Override
    public Token selectByToken(String token) {
        return tokenMapper.selectByToken(token);
    }
}
