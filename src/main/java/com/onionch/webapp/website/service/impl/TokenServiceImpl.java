package com.onionch.webapp.website.service.impl;

import com.onionch.webapp.website.bean.Token;
import com.onionch.webapp.website.bean.User;
import com.onionch.webapp.website.bean.enums.ResultCode;
import com.onionch.webapp.website.bean.request.LoginRequest;
import com.onionch.webapp.website.bean.response.RestResponse;
import com.onionch.webapp.website.mapper.TokenMapper;
import com.onionch.webapp.website.mapper.UserMapper;
import com.onionch.webapp.website.service.TokenService;
import com.onionch.webapp.website.util.DESUtil;
import com.onionch.webapp.website.util.PrePageUtil;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service("tokenServiceImpl")
public class TokenServiceImpl implements TokenService {

    private static final Logger logger = Logger.getLogger(TokenServiceImpl.class);

    @Autowired
    private TokenMapper tokenMapper;

    @Autowired
    private UserMapper userMapper;


    @Override
    public RestResponse create(LoginRequest loginRequest) {
        try {
            User user = userMapper.selectByName(loginRequest.getUsername());
            if (null == user) {
                return RestResponse.failure(ResultCode.USER_NOT_EXIST);
            }
            if (DESUtil.decryptString(user.getUserPassword()).equals(loginRequest.getPassword())) {
                Token token = tokenMapper.selectByUid(user.getuId());
                if (null == token) {
                    token = new Token();
                    token.setToken(RandomStringUtils.randomAlphanumeric(32));
                    token.setuId(user.getuId());
                    token.setRoleId(user.getRoleId());
                    tokenMapper.create(token);
                }
                tokenMapper.updateLastDate(token.getToken());
                return RestResponse.success(token.getToken());
            } else {
                return RestResponse.failure(ResultCode.PASSWORD_ERROR);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return RestResponse.failure(ResultCode.SYSTEM_ERROR);
        }
    }

    @Override
    public RestResponse listTokens(String uId, Integer pageIndex, Integer pageSize) {
        try {
            Integer totalCount = tokenMapper.allCount(uId);

            Integer prePageIndex = ((pageIndex - 1) * pageSize);
            Map map = new HashMap();
            map.put("list", tokenMapper.all(uId, prePageIndex, pageSize));
            map.put("totalCount", totalCount);
            map.put("totalPage", PrePageUtil.pageCount(pageSize, totalCount));
            map.put("currentPage", pageIndex);
            map.put("viewTotal", pageSize);
            return RestResponse.success(map);
        } catch (Exception e) {
            logger.error("TOKEN SERVICE ALL ERROR:" + e.getMessage());
            return RestResponse.failure(ResultCode.SYSTEM_ERROR);
        }
    }

    @Override
    public RestResponse delete(String uId) {
        try {
            if(null == tokenMapper.selectByToken(uId)){
                return RestResponse.failure(ResultCode.TOKEN_NOT_EXIST);
            }
            tokenMapper.delete(uId);
            return RestResponse.success(null);
        }catch (Exception e){
            logger.error("TOKEN SERVICE DELETE ERROR:" + e.getMessage());
            return RestResponse.failure(ResultCode.SYSTEM_ERROR);
        }
    }
}
