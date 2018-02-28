package com.onionch.webapp.website.controller;

import com.onionch.webapp.website.bean.enums.ResultCode;
import com.onionch.webapp.website.bean.request.LoginRequest;
import com.onionch.webapp.website.bean.response.RestResponse;
import com.onionch.webapp.website.service.TokenService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/*")
public class TokenController {

    private static final Logger logger = Logger.getLogger(TokenController.class);

    @Autowired
    private TokenService tokenService;

    @RequestMapping(value = "/tokens", method = RequestMethod.GET)
    private @ResponseBody
    RestResponse listTokens(@RequestParam(value = "uId", required = false) String uId,
                            @RequestParam(value = "pageIndex", required = false, defaultValue = "1") Integer pageIndex,
                            @RequestParam(value = "pageSize", required = false, defaultValue = "50") Integer pageSize) {

        return tokenService.listTokens(uId,pageIndex,pageSize);
    }

    @RequestMapping(value = "/tokens", method = RequestMethod.POST)
    private RestResponse create(@RequestBody LoginRequest loginRequest) {
        if (StringUtils.isEmpty(loginRequest.getUsername())) {
            return RestResponse.failure(ResultCode.PARAM_IS_BLANK, "username is empty");
        }
        if (StringUtils.isEmpty(loginRequest.getPassword())) {
            return RestResponse.failure(ResultCode.PARAM_IS_BLANK, "password is empty");
        }
        return tokenService.create(loginRequest);
    }

    @RequestMapping(value = "/tokens/{token}", method = RequestMethod.DELETE)
    private @ResponseBody
    RestResponse delete(@PathVariable("token") String token) {
        if (StringUtils.isEmpty(token)) {
            return RestResponse.failure(ResultCode.PARAM_IS_BLANK, "token is empty");
        }
        return tokenService.delete(token);
    }

}
