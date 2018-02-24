package com.onionch.webapp.website.service;

import com.onionch.webapp.website.bean.request.UserRequest;
import com.onionch.webapp.website.bean.response.RestResponse;

public interface UserService {
    RestResponse listAll(String userName,Integer pageIndex,Integer pageSize);
    RestResponse create(UserRequest userRequest);
    RestResponse update(String uId,UserRequest userRequest);
    RestResponse delete(String uId);
}
