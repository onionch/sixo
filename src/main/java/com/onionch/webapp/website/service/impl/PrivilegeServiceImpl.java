package com.onionch.webapp.website.service.impl;

import com.onionch.webapp.website.bean.Privilege;
import com.onionch.webapp.website.mapper.PrivilegeMapper;
import com.onionch.webapp.website.service.PrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("privilegeServiceImpl")
public class PrivilegeServiceImpl implements PrivilegeService {

    @Autowired
    private PrivilegeMapper privilegeMapper;

    @Override
    public List<Privilege> listAll() {
        return privilegeMapper.listAll();
    }

    @Override
    public void create(Privilege privilege) {
        privilegeMapper.create(privilege);
    }

    @Override
    public void update(Privilege privilege) {
        privilegeMapper.update(privilege);
    }

    @Override
    public void delete(String uId) {
        privilegeMapper.delete(uId);
    }

    @Override
    public Privilege findByRoleId(int roleId, int resId) {
        return privilegeMapper.findByRoleId(roleId,resId);
    }
}
