package com.onionch.webapp.website.controller;

import com.onionch.webapp.website.bean.response.RestResponse;
import com.onionch.webapp.website.bean.enums.ResultCode;
import com.onionch.webapp.website.bean.request.MenuRequest;
import com.onionch.webapp.website.service.MenuService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/*")
public class MenuController {

    private static final Logger logger = Logger.getLogger(MenuController.class);

    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "/menus", method = RequestMethod.GET)
    private @ResponseBody
    RestResponse listMenus() {
        return menuService.listMenu();
    }

    @RequestMapping(value = "/menus", method = RequestMethod.POST)
    private @ResponseBody
    RestResponse create(@RequestBody MenuRequest menuRequest) {

        if (StringUtils.isEmpty(menuRequest.getMenuName())) {
            return RestResponse.failure(ResultCode.PARAM_IS_BLANK, "menu name is empty");
        }
        if (StringUtils.isEmpty(menuRequest.getUrl())) {
            return RestResponse.failure(ResultCode.PARAM_IS_BLANK, "menu url is empty");
        }
        if (StringUtils.isEmpty(menuRequest.getAccess())) {
            return RestResponse.failure(ResultCode.PARAM_IS_BLANK, "menu access is empty");
        }
        return menuService.create(menuRequest);
    }

    @RequestMapping(value = "/menus/{menuId}", method = RequestMethod.DELETE)
    private @ResponseBody
    RestResponse delete(@PathVariable("menuId") String menuId) {
        if (StringUtils.isEmpty(menuId)) {
            return RestResponse.failure(ResultCode.PARAM_IS_BLANK, "menu id is empty");
        }
        return menuService.delete(menuId);
    }

    @RequestMapping(value = "/menus/{menuId}", method = RequestMethod.PUT)
    private @ResponseBody
    RestResponse update(@PathVariable("menuId") String menuId, @RequestBody MenuRequest menuRequest) {
        if (StringUtils.isEmpty(menuId)) {
            return RestResponse.failure(ResultCode.PARAM_IS_BLANK, "menu id is empty");
        }
        return menuService.update(menuId, menuRequest);
    }
}
