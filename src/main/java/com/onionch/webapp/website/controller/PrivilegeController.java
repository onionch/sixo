package com.onionch.webapp.website.controller;


import com.onionch.webapp.website.bean.Privilege;
import com.onionch.webapp.website.bean.response.RestResponse;
import com.onionch.webapp.website.service.PrivilegeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/privilege")
public class PrivilegeController {

    private static final Logger logger = Logger.getLogger(PrivilegeController.class);

    @Autowired
    private PrivilegeService privilegeService;

    @RequestMapping(method = RequestMethod.GET)
    private @ResponseBody
    RestResponse listAllPrivileges(){
        List list=privilegeService.listAll();
        return RestResponse.response(200,"success",list);
    }

    @RequestMapping(method = RequestMethod.POST)
    private @ResponseBody RestResponse create(@RequestBody Privilege privilege){
        privilegeService.create(privilege);
        return RestResponse.response(200,"success","");
    }

    @RequestMapping(value = "/{uId}",method = RequestMethod.DELETE)
    private @ResponseBody RestResponse delete(@PathVariable String uId){
        privilegeService.delete(uId);
        return RestResponse.response(200,"success","");
    }

    @RequestMapping(method = RequestMethod.PUT)
    private @ResponseBody RestResponse update(@RequestBody Privilege privilege){
        privilegeService.update(privilege);
        return RestResponse.response(200,"success","");
    }
}
