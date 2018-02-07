package com.onionch.webapp.website.service;

import com.onionch.webapp.website.bean.Role;

import java.util.List;

public interface RoleService {
    void create(Role role);
    void update(Role role);
    void delete(String rId);
    List<Role> listAll();
    Role findRoleById(int id);
}
