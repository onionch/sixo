package com.onionch.webapp.website.mapper;

import com.onionch.webapp.website.bean.Token;

public interface TokenMapper {
    void create(Token token);
    void updateLastDate();
    void delete(String uId);
    Token selectByUid(String uId);
    Token selectByToken(String token);
}
