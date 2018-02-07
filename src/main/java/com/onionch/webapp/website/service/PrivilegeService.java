package com.onionch.webapp.website.service;

import com.onionch.webapp.website.bean.Privilege;

import java.util.List;

public interface PrivilegeService {
    List<Privilege> listAll();
    void create(Privilege privilege);
    void update(Privilege privilege);
    void delete(String uId);
    Privilege findByRoleId(int roleId,int resId);
}
