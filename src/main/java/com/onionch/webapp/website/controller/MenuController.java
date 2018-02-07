package com.onionch.webapp.website.controller;

import com.onionch.webapp.website.bean.Menu;
import com.onionch.webapp.website.bean.RestResponse;
import com.onionch.webapp.website.service.MenuService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/menu")
public class MenuController {

    private static final Logger logger = Logger.getLogger(MenuController.class);

    @Autowired
    private MenuService menuService;

    @RequestMapping(method = RequestMethod.GET)
    private @ResponseBody
    RestResponse listAll(){
        List list=menuService.listAll();
        return RestResponse.response(200,"success",list);
    }

    @RequestMapping(method = RequestMethod.POST)
    private @ResponseBody RestResponse create(@RequestBody Menu menu){
        menuService.create(menu);
        return RestResponse.response(200,"success","");
    }

    @RequestMapping(value = "/{uId}",method = RequestMethod.DELETE)
    private @ResponseBody RestResponse delete(@PathVariable String uId){
        menuService.delete(uId);
        return RestResponse.response(200,"success","");
    }

    @RequestMapping(method = RequestMethod.PUT)
    private @ResponseBody RestResponse update(@RequestBody Menu menu){
        menuService.update(menu);
        return RestResponse.response(200,"success","");
    }

}
