package com.onionch.webapp.website.controller;

import com.onionch.webapp.website.bean.request.RoleRequest;
import com.onionch.webapp.website.bean.response.RestResponse;
import com.onionch.webapp.website.bean.Role;
import com.onionch.webapp.website.service.RoleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/*")
public class RoleController {

    private static final Logger logger = Logger.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/role", method = RequestMethod.GET)
    private @ResponseBody
    RestResponse listRoles() {
        return roleService.listAll();
    }

    @RequestMapping(value = "/role",method = RequestMethod.POST)
    private @ResponseBody
    RestResponse create(@RequestBody RoleRequest roleRequest) {
        return roleService.create(roleRequest);
    }

    @RequestMapping(value = "/role/{uId}", method = RequestMethod.DELETE)
    private @ResponseBody
    RestResponse delete(@PathVariable String uId) {
        roleService.delete(uId);
        return RestResponse.response(200, "success", "");
    }

    @RequestMapping(value = "/role/{uId}", method = RequestMethod.PUT)
    private @ResponseBody
    RestResponse update(@PathVariable String uId,@RequestBody RoleRequest roleRequest) {
//        roleService.update(role);
        return RestResponse.response(200, "success", "");
    }

}
