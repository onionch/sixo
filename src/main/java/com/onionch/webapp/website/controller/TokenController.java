package com.onionch.webapp.website.controller;

import com.onionch.webapp.website.bean.*;
import com.onionch.webapp.website.service.RoleService;
import com.onionch.webapp.website.service.TokenService;
import com.onionch.webapp.website.service.UserService;
import com.onionch.webapp.website.staticless.TokenStatic;
import com.onionch.webapp.website.util.DESUtil;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/token")
public class TokenController {

    private static final Logger logger = Logger.getLogger(TokenController.class);

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/{uId}", method = RequestMethod.GET)
    private @ResponseBody
    RestResponse get(@PathVariable String uId) {
        Token token1 = tokenService.selectByUid(uId);
        return RestResponse.response(200, "success", token1.getToken());
    }

    @RequestMapping(method = RequestMethod.POST)
    private RestResponse create(@RequestBody LoginRequest loginRequest) {
        if (StringUtils.isEmpty(loginRequest.getUsername())) {
            return RestResponse.response(500, "failed", "username can not be none");
        }
        User user = userService.selectByName(loginRequest.getUsername());
        if (null == user) {
            return RestResponse.response(500, "failed", "no such user");
        }
        if (DESUtil.decryptString(user.getUserPassword()).equals(loginRequest.getPassword())) {
            //保存用户token
            Map map=new HashMap();
            Role role=roleService.findRoleById(user.getRoleId());
            try {
                Token token = tokenService.selectByUid(user.getuId());
                if (null == token) {
                    Token newToken = new Token();
                    String tokenContent = RandomStringUtils.randomAlphanumeric(32);
                    newToken.setToken(tokenContent);
                    newToken.setuId(user.getuId());
                    newToken.setRoleId(user.getRoleId());
                    tokenService.create(newToken);
                    map.put("token",newToken.getToken());
                    map.put("access",role.getAccess());
                    map.put("roleName",role.getRoleName());
                    return RestResponse.response(200, "success", map);
                } else {
                    map.put("token",token.getToken());
                    map.put("access",role.getAccess());
                    map.put("roleName",role.getRoleName());
                    return RestResponse.response(200, "success", map);
                }
            } catch (Exception e) {
                logger.error(e.getMessage());
                return RestResponse.response(500, "系统错误", "");
            }
        } else {
            return RestResponse.response(500, "failed", "password error");
        }
    }


}
