package com.onionch.webapp.website.controller;

import com.onionch.webapp.website.bean.enums.ResultCode;
import com.onionch.webapp.website.bean.request.ResourceRequest;
import com.onionch.webapp.website.bean.response.RestResponse;
import com.onionch.webapp.website.service.ResourceService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/*")
public class ResourceController {

    private static final Logger logger = Logger.getLogger(ResourceController.class);

    @Autowired
    private ResourceService resourceService;

    @RequestMapping(value = "/resources", method = RequestMethod.GET)
    private @ResponseBody
    RestResponse listResources(@RequestParam(value = "resName", required = false) String resName,
                               @RequestParam(value = "pageIndex", required = false, defaultValue = "1") Integer pageIndex,
                               @RequestParam(value = "pageSize", required = false, defaultValue = "50") Integer pageSize) {
        return resourceService.listAll(resName, pageIndex, pageSize);
    }

    @RequestMapping(value = "/resources", method = RequestMethod.POST)
    private @ResponseBody
    RestResponse create(@RequestBody ResourceRequest resourceRequest) {
        if (StringUtils.isEmpty(resourceRequest.getResName())) {
            return RestResponse.failure(ResultCode.PARAM_IS_BLANK, "res name is empty");
        }
        if (StringUtils.isEmpty(resourceRequest.getResDesc())) {
            return RestResponse.failure(ResultCode.PARAM_IS_BLANK, "res desc is empty");
        }
        return resourceService.create(resourceRequest);
    }

    @RequestMapping(value = "/resources/{uId}", method = RequestMethod.DELETE)
    private @ResponseBody
    RestResponse delete(@PathVariable String uId) {
        if (StringUtils.isEmpty(uId)) {
            return RestResponse.failure(ResultCode.PARAM_IS_BLANK, "res id is empty");
        }
        return resourceService.delete(uId);
    }

    @RequestMapping(value = "/resources/{uId}", method = RequestMethod.PUT)
    private @ResponseBody
    RestResponse update(@PathVariable String uId, @RequestBody ResourceRequest resourceRequest) {
        if (StringUtils.isEmpty(uId)) {
            return RestResponse.failure(ResultCode.PARAM_IS_BLANK, "res id is empty");
        }
        return resourceService.update(uId, resourceRequest);
    }
}
