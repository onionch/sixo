package com.onionch.webapp.website.service;

import com.onionch.webapp.website.bean.Role;
import com.onionch.webapp.website.bean.request.RoleRequest;
import com.onionch.webapp.website.bean.response.RestResponse;

import java.util.List;

public interface RoleService {
    RestResponse create(RoleRequest role);
    RestResponse update(String roleId,RoleRequest role);
    RestResponse delete(String rId);
    RestResponse listAll();
    Role findRoleById(int id);
}
