package com.onionch.webapp.website.mapper;

import com.onionch.webapp.website.bean.Token;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TokenMapper {
    List<Token> all(@Param("uId") String uId, @Param("pageIndex") Integer pageIndex, @Param("pageSize") Integer pageSize);

    Integer allCount(@Param("uId") String uId);

    void create(Token token);

    void updateLastDate(@Param("token") String token);

    void delete(@Param("token") String token);

    Token selectByUid(String uId);

    Token selectByToken(String token);
}
