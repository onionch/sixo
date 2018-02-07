package com.onionch.webapp.website.controller;

import com.onionch.webapp.website.bean.RestResponse;
import com.onionch.webapp.website.bean.User;
import com.onionch.webapp.website.service.UserService;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private static final Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    private @ResponseBody RestResponse listAllUsers(){
        List list=userService.listAll();
        return RestResponse.response(200,"success",list);
    }

    @RequestMapping(method = RequestMethod.POST)
    private @ResponseBody RestResponse create(@RequestBody User user){
        user.setuId(RandomStringUtils.randomNumeric(10));
        userService.create(user);
        return RestResponse.response(200,"success","");
    }

    @RequestMapping(value = "/{uId}",method = RequestMethod.DELETE)
    private @ResponseBody RestResponse delete(@PathVariable String uId){
        userService.delete(uId);
        return RestResponse.response(200,"success","");
    }

    @RequestMapping(method = RequestMethod.PUT)
    private @ResponseBody RestResponse update(@RequestBody User user){
        userService.update(user);
        return RestResponse.response(200,"success","");
    }
}
