package com.onionch.webapp.website.controller;

import com.onionch.webapp.website.bean.enums.ResultCode;
import com.onionch.webapp.website.bean.request.UserRequest;
import com.onionch.webapp.website.bean.response.RestResponse;
import com.onionch.webapp.website.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/*")
public class UserController {

    private static final Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/users",method = RequestMethod.GET)
    private @ResponseBody RestResponse listUsers(@RequestParam(value = "userName", required = false) String userName,
                                                 @RequestParam(value = "pageIndex", required = false, defaultValue = "1") Integer pageIndex,
                                                 @RequestParam(value = "pageSize", required = false, defaultValue = "50") Integer pageSize){
        return userService.listAll(userName,pageIndex,pageSize);
    }

    @RequestMapping(value = "/users",method = RequestMethod.POST)
    private @ResponseBody RestResponse create(@RequestBody UserRequest userRequest){
        if (StringUtils.isEmpty(userRequest.getUserName())) {
            return RestResponse.failure(ResultCode.PARAM_IS_BLANK, "username is empty");
        }
        if (StringUtils.isEmpty(userRequest.getUserPassword())) {
            return RestResponse.failure(ResultCode.PARAM_IS_BLANK, "password is empty");
        }
        return userService.create(userRequest);
    }

    @RequestMapping(value = "/users/{uId}",method = RequestMethod.DELETE)
    private @ResponseBody RestResponse delete(@PathVariable String uId){
        if (StringUtils.isEmpty(uId)) {
            return RestResponse.failure(ResultCode.PARAM_IS_BLANK, "uId is empty");
        }
        return userService.delete(uId);
    }

    @RequestMapping(value = "/users/{uId}",method = RequestMethod.PUT)
    private @ResponseBody RestResponse update(@PathVariable String uId,@RequestBody UserRequest userRequest){
        if (StringUtils.isEmpty(uId)) {
            return RestResponse.failure(ResultCode.PARAM_IS_BLANK, "uId is empty");
        }
        return userService.update(uId,userRequest);
    }
}
