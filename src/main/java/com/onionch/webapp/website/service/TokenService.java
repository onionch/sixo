package com.onionch.webapp.website.service;
import com.onionch.webapp.website.bean.request.LoginRequest;
import com.onionch.webapp.website.bean.response.RestResponse;

public interface TokenService {
    RestResponse create(LoginRequest loginRequest);
    RestResponse listTokens(String uId,Integer pageIndex,Integer pageSize);
    RestResponse delete(String uId);
}
