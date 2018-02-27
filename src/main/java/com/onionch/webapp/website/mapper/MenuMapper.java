package com.onionch.webapp.website.mapper;

import com.onionch.webapp.website.bean.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuMapper {
    void create(Menu menu);

    void update(Menu menu);

    void delete(@Param("serialNum") String serialNum);

    List<Menu> listAll();

    Menu findBySerialNumber(@Param("serialNum") String serialNum);

    List<Menu> listMenuByUserName(@Param("userName") String userName);
}
