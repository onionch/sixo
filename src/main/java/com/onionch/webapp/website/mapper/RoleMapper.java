package com.onionch.webapp.website.mapper;

import com.onionch.webapp.website.bean.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    void create(Role role);
    void update(Role role);
    void delete(@Param("serialNum") String serialNum);
    List<Role> listAll();
    Role findRoleById(int id);
    Role findBySerialNumber(@Param("serialNum") String serialNum);
}
