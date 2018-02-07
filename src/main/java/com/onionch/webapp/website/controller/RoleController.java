package com.onionch.webapp.website.controller;

import com.onionch.webapp.website.bean.RestResponse;
import com.onionch.webapp.website.bean.Role;
import com.onionch.webapp.website.bean.User;
import com.onionch.webapp.website.service.RoleService;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/role")
public class RoleController {

    private static final Logger logger = Logger.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;

    @RequestMapping(method = RequestMethod.GET)
    private @ResponseBody
    RestResponse listAllRoles(){
        List list=roleService.listAll();
        return RestResponse.response(200,"success",list);
    }

    @RequestMapping(method = RequestMethod.POST)
    private @ResponseBody RestResponse create(@RequestBody Role role){
        roleService.create(role);
        return RestResponse.response(200,"success","");
    }

    @RequestMapping(value = "/{uId}",method = RequestMethod.DELETE)
    private @ResponseBody RestResponse delete(@PathVariable String uId){
        roleService.delete(uId);
        return RestResponse.response(200,"success","");
    }

    @RequestMapping(method = RequestMethod.PUT)
    private @ResponseBody RestResponse update(@RequestBody Role role){
        roleService.update(role);
        return RestResponse.response(200,"success","");
    }

}
