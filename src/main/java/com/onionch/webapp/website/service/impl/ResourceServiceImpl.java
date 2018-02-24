package com.onionch.webapp.website.service.impl;

import com.onionch.webapp.website.bean.Resource;
import com.onionch.webapp.website.bean.enums.ResultCode;
import com.onionch.webapp.website.bean.request.ResourceRequest;
import com.onionch.webapp.website.bean.response.RestResponse;
import com.onionch.webapp.website.mapper.ResourceMapper;
import com.onionch.webapp.website.service.ResourceService;
import com.onionch.webapp.website.util.EncryptUtil;
import com.onionch.webapp.website.util.PrePageUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service("resourceServiceImpl")
public class ResourceServiceImpl implements ResourceService {

    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public RestResponse listAll(String resName, Integer pageIndex, Integer pageSize) {
        try {
            Integer totalCount = resourceMapper.allCount(resName);
            Integer prePageIndex = ((pageIndex - 1) * pageSize);
            Map map = new HashMap();
            map.put("list", resourceMapper.all(resName, prePageIndex, pageSize));
            map.put("totalCount", totalCount);
            map.put("totalPage", PrePageUtil.pageCount(pageSize, totalCount));
            map.put("currentPage", pageIndex);
            map.put("viewTotal", pageSize);
            return RestResponse.success(map);
        } catch (Exception e) {
            logger.error("RESOURCE SERVICE ALL ERROR:" + e.getMessage());
            return RestResponse.failure(ResultCode.SYSTEM_ERROR);
        }
    }

    @Override
    public RestResponse create(ResourceRequest resourceRequest) {
        try {
            if(null != resourceMapper.findByName(resourceRequest.getResName())){
                return RestResponse.failure(ResultCode.RES_ALREADY_EXIST);
            }
            Resource resource=new Resource();
            resource.setResName(resourceRequest.getResName());
            resource.setResDesc(resourceRequest.getResDesc());
            resource.setSerialNum(EncryptUtil.generate32(resource.getResName() + new Date().getTime()));
            resourceMapper.create(resource);
            return RestResponse.success(null);
        } catch (Exception e) {
            logger.error("RESOURCE SERVICE CREATE ERROR:" + e.getMessage());
            return RestResponse.failure(ResultCode.SYSTEM_ERROR);
        }
    }

    @Override
    public RestResponse update(String resId,ResourceRequest resourceRequest) {
        try {
            Resource resource=resourceMapper.findBySerialNum(resId);
            if(null == resource){
                return RestResponse.failure(ResultCode.RES_NOT_EXIST);
            }
            if(StringUtils.isNotBlank(resourceRequest.getResName())){
                resource.setResName(resourceRequest.getResName());
            }
            if(StringUtils.isNotBlank(resourceRequest.getResDesc())){
                resource.setResDesc(resourceRequest.getResDesc());
            }
            resourceMapper.update(resource);
            return RestResponse.success(null);
        } catch (Exception e) {
            logger.error("RESOURCE SERVICE UPDATE ERROR:" + e.getMessage());
            return RestResponse.failure(ResultCode.SYSTEM_ERROR);
        }
    }

    @Override
    public RestResponse delete(String uId) {
        try {
            Resource resource=resourceMapper.findBySerialNum(uId);
            if(null == resource){
                return RestResponse.failure(ResultCode.RES_NOT_EXIST);
            }
            resourceMapper.delete(uId);
            return RestResponse.success(null);
        } catch (Exception e) {
            logger.error("RESOURCE SERVICE DELETE ERROR:" + e.getMessage());
            return RestResponse.failure(ResultCode.SYSTEM_ERROR);
        }
    }

}
