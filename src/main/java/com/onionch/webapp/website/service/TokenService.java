package com.onionch.webapp.website.service;

import com.onionch.webapp.website.bean.Token;

public interface TokenService {
    void create(Token token);
    void updateLastDate();
    void delete(String uId);
    Token selectByUid(String uId);
    Token selectByToken(String token);
}
