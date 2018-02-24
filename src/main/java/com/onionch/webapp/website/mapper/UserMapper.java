package com.onionch.webapp.website.mapper;

import com.onionch.webapp.website.bean.Token;
import com.onionch.webapp.website.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    List<User> all(@Param("userName") String userName, @Param("pageIndex") Integer pageIndex, @Param("pageSize") Integer pageSize);

    Integer allCount(@Param("userName") String userName);

    List<User> listAll();

    void create(User user);

    void update(User user);

    void delete(String uId);

    User selectByName(String userName);

    User selectByUid(String uId);
}

