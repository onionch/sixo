package com.onionch.webapp.website.controller;

import com.onionch.webapp.website.bean.LoginRequest;
import com.onionch.webapp.website.bean.RestResponse;
import com.onionch.webapp.website.bean.Token;
import com.onionch.webapp.website.bean.User;
import com.onionch.webapp.website.service.TokenService;
import com.onionch.webapp.website.service.UserService;
import com.onionch.webapp.website.staticless.TokenStatic;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/token")
public class TokenController {

    private static final Logger logger = Logger.getLogger(TokenController.class);

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

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
        if (user.getUserPassword().equals(loginRequest.getPassword())) {
            //保存用户token
            try {
                Token token = tokenService.selectByUid(user.getuId());
                if (null == token) {
                    Token newToken = new Token();
                    String tokenContent = RandomStringUtils.randomAlphanumeric(32);
                    newToken.setToken(tokenContent);
                    newToken.setuId(user.getuId());
                    tokenService.create(newToken);
                    return RestResponse.response(200, "success", newToken.getToken());
                } else {
                    return RestResponse.response(200, "success", token.getToken());
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
