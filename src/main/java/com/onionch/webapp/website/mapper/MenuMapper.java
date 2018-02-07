package com.onionch.webapp.website.mapper;

import com.onionch.webapp.website.bean.Menu;

import java.util.List;

public interface MenuMapper {
    void create(Menu menu);
    void update(Menu menu);
    void delete(String id);
    List<Menu> listAll();
}
