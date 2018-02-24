package com.onionch.webapp.website.service.impl;

import com.onionch.webapp.website.bean.Role;
import com.onionch.webapp.website.bean.enums.ResultCode;
import com.onionch.webapp.website.bean.request.RoleRequest;
import com.onionch.webapp.website.bean.response.RestResponse;
import com.onionch.webapp.website.mapper.RoleMapper;
import com.onionch.webapp.website.service.RoleService;
import com.onionch.webapp.website.util.EncryptUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("roleServiceImpl")
public class RoleServiceImpl implements RoleService {

    private static final Logger logger = Logger.getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public RestResponse create(RoleRequest roleRequest) {
        try {
            Role role=new Role();
            role.setAccess(roleRequest.getAccess());
            role.setRoleName(roleRequest.getRoleDesc());
            role.setRoleDesc(roleRequest.getRoleDesc());
            role.setSerialNum(EncryptUtil.generate32(role.getRoleName() + new Date().getTime()));
            roleMapper.create(role);
            return RestResponse.success(null);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return RestResponse.failure(ResultCode.SYSTEM_ERROR);
        }
    }

    @Override
    public RestResponse update(String roleId,RoleRequest roleRequest) {

       return null;
    }

    @Override
    public RestResponse delete(String rId) {
        try {
            roleMapper.delete(rId);
            return RestResponse.success(null);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return RestResponse.failure(ResultCode.SYSTEM_ERROR);
        }
    }

    @Override
    public RestResponse listAll() {
        try {
            return RestResponse.success(roleMapper.listAll());
        } catch (Exception e) {
            logger.error(e.getMessage());
            return RestResponse.failure(ResultCode.SYSTEM_ERROR);
        }
    }

    @Override
    public Role findRoleById(int id) {
        return roleMapper.findRoleById(id);
    }
}
