package com.onionch.webapp.website.mapper;

import com.onionch.webapp.website.bean.Role;

import java.util.List;

public interface RoleMapper {
    void create(Role role);
    void update(Role role);
    void delete(String rId);
    List<Role> listAll();
    Role findRoleById(int id);
}
