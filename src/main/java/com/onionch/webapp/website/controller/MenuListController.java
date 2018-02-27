package com.onionch.webapp.website.controller;

import com.onionch.webapp.website.bean.enums.ResultCode;
import com.onionch.webapp.website.bean.request.MenuListRequest;
import com.onionch.webapp.website.bean.request.MenuRequest;
import com.onionch.webapp.website.bean.response.RestResponse;
import com.onionch.webapp.website.service.MenuService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/*")
public class MenuListController {

    private static final Logger logger = Logger.getLogger(MenuListController.class);

    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "/menuList/{userName}", method = RequestMethod.GET)
    private @ResponseBody
    RestResponse listMenus(@PathVariable("userName") String userName) {
        if (StringUtils.isEmpty(userName)) {
            return RestResponse.failure(ResultCode.PARAM_IS_BLANK, "user name is empty");
        }
        return menuService.listMenuByUserName(userName);
    }

}
