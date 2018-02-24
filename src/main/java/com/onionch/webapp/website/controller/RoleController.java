package com.onionch.webapp.website.controller;

import com.onionch.webapp.website.bean.enums.ResultCode;
import com.onionch.webapp.website.bean.request.RoleRequest;
import com.onionch.webapp.website.bean.response.RestResponse;
import com.onionch.webapp.website.bean.Role;
import com.onionch.webapp.website.service.RoleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/*")
public class RoleController {

    private static final Logger logger = Logger.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/roles", method = RequestMethod.GET)
    private @ResponseBody
    RestResponse listRoles() {
        return roleService.listAll();
    }

    @RequestMapping(value = "/roles", method = RequestMethod.POST)
    private @ResponseBody
    RestResponse create(@RequestBody RoleRequest roleRequest) {
        if (StringUtils.isEmpty(roleRequest.getRoleName())) {
            return RestResponse.failure(ResultCode.PARAM_IS_BLANK, "role name is empty");
        }
        if (StringUtils.isEmpty(roleRequest.getRoleDesc())) {
            return RestResponse.failure(ResultCode.PARAM_IS_BLANK, "role desc is empty");
        }
        return roleService.create(roleRequest);
    }

    @RequestMapping(value = "/roles/{roleId}", method = RequestMethod.DELETE)
    private @ResponseBody
    RestResponse delete(@PathVariable String roleId) {
        if (StringUtils.isEmpty(roleId)) {
            return RestResponse.failure(ResultCode.PARAM_IS_BLANK, "role Id access is empty");
        }
        return roleService.delete(roleId);
    }

    @RequestMapping(value = "/roles/{roleId}", method = RequestMethod.PUT)
    private @ResponseBody
    RestResponse update(@PathVariable String roleId, @RequestBody RoleRequest roleRequest) {
        if (StringUtils.isEmpty(roleId)) {
            return RestResponse.failure(ResultCode.PARAM_IS_BLANK, "role Id access is empty");
        }
        return roleService.update(roleId, roleRequest);
    }

}
