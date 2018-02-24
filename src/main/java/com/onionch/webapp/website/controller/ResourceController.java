package com.onionch.webapp.website.controller;

import com.onionch.webapp.website.bean.Resource;
import com.onionch.webapp.website.bean.response.RestResponse;
import com.onionch.webapp.website.service.ResourceService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/resource")
public class ResourceController {

    private static final Logger logger = Logger.getLogger(ResourceController.class);

    @Autowired
    private ResourceService resourceService;

    @RequestMapping(method = RequestMethod.GET)
    private @ResponseBody
    RestResponse listAllResources(){
        List list=resourceService.listAll();
        return RestResponse.response(200,"success",list);
    }

    @RequestMapping(method = RequestMethod.POST)
    private @ResponseBody RestResponse create(@RequestBody Resource resource){
        resourceService.create(resource);
        return RestResponse.response(200,"success","");
    }

    @RequestMapping(value = "/{uId}",method = RequestMethod.DELETE)
    private @ResponseBody RestResponse delete(@PathVariable String uId){
        resourceService.delete(uId);
        return RestResponse.response(200,"success","");
    }

    @RequestMapping(method = RequestMethod.PUT)
    private @ResponseBody RestResponse update(@RequestBody Resource resource){
        resourceService.update(resource);
        return RestResponse.response(200,"success","");
    }
}
