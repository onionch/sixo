package com.onionch.webapp.website.mapper;

import com.onionch.webapp.website.bean.Privilege;
import com.onionch.webapp.website.bean.Resource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PrivilegeMapper {
    List<Privilege> listAll();
    void create(Privilege privilege);
    void update(Privilege privilege);
    void delete(String uId);
    Privilege findByRoleId(@Param("roleId") int roleId,@Param("resId") int resId);
}

