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
import org.springframework.util.StringUtils;
import java.util.Date;

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
        try {
            Role role=roleMapper.findBySerialNumber(roleId);
            if(null == role){
                return RestResponse.failure(ResultCode.ROLE_NOT_EXIST);
            }
            if(!StringUtils.isEmpty(roleRequest.getAccess())){
                role.setAccess(roleRequest.getAccess());
            }
            if(!StringUtils.isEmpty(roleRequest.getRoleDesc())){
                role.setRoleDesc(roleRequest.getRoleDesc());
            }
            if(!StringUtils.isEmpty(roleRequest.getRoleName())){
                role.setRoleName(roleRequest.getRoleName());
            }
            roleMapper.update(role);
            return RestResponse.success(null);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return RestResponse.failure(ResultCode.SYSTEM_ERROR);
        }
    }

    @Override
    public RestResponse delete(String roleId) {
        try {
            Role role=roleMapper.findBySerialNumber(roleId);
            if(null == role){
                return RestResponse.failure(ResultCode.ROLE_NOT_EXIST);
            }
            roleMapper.delete(roleId);
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
