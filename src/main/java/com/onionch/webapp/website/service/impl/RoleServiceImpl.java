package com.onionch.webapp.website.service.impl;

import com.onionch.webapp.website.bean.Role;
import com.onionch.webapp.website.mapper.RoleMapper;
import com.onionch.webapp.website.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("roleServiceImpl")
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public void create(Role role) {
        roleMapper.create(role);
    }

    @Override
    public void update(Role role) {
        roleMapper.update(role);
    }

    @Override
    public void delete(String rId) {
        roleMapper.delete(rId);
    }

    @Override
    public List<Role> listAll() {
        return roleMapper.listAll();
    }

    @Override
    public Role findRoleById(int id) {
        return roleMapper.findRoleById(id);
    }
}
