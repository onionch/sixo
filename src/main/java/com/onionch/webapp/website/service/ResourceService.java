package com.onionch.webapp.website.service;
import com.onionch.webapp.website.bean.request.ResourceRequest;
import com.onionch.webapp.website.bean.response.RestResponse;

public interface ResourceService {
    RestResponse listAll(String resName,Integer pageIndex,Integer pageSize);
    RestResponse create(ResourceRequest resource);
    RestResponse update(String uId,ResourceRequest resource);
    RestResponse delete(String uId);
}
