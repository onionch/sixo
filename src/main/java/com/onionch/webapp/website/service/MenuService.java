package com.onionch.webapp.website.service;

import com.onionch.webapp.website.bean.Menu;

import java.util.List;

public interface MenuService {
    void create(Menu menu);
    void update(Menu menu);
    void delete(String id);
    List<Menu> listAll();
}
