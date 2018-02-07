package com.onionch.webapp.website.service.impl;

import com.onionch.webapp.website.bean.Menu;
import com.onionch.webapp.website.mapper.MenuMapper;
import com.onionch.webapp.website.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("menuServiceImpl")
public class MenuServiceImpl implements MenuService{

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public void create(Menu menu) {
        menuMapper.create(menu);
    }

    @Override
    public void update(Menu menu) {
        menuMapper.update(menu);
    }

    @Override
    public void delete(String id) {
        menuMapper.delete(id);
    }

    @Override
    public List<Menu> listAll() {
        return menuMapper.listAll();
    }
}
