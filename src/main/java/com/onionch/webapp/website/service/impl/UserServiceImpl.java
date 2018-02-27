package com.onionch.webapp.website.service.impl;

import com.onionch.webapp.website.bean.User;
import com.onionch.webapp.website.bean.enums.ResultCode;
import com.onionch.webapp.website.bean.request.UserRequest;
import com.onionch.webapp.website.bean.response.RestResponse;
import com.onionch.webapp.website.mapper.UserMapper;
import com.onionch.webapp.website.service.UserService;
import com.onionch.webapp.website.util.DESUtil;
import com.onionch.webapp.website.util.PrePageUtil;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService{

    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    public RestResponse listAll(String userName, Integer pageIndex, Integer pageSize){
        try {
            Integer totalCount = userMapper.allCount(userName);
            Integer prePageIndex = ((pageIndex - 1) * pageSize);
            Map map = new HashMap();
            map.put("list", userMapper.all(userName, prePageIndex, pageSize));
            map.put("totalCount", totalCount);
            map.put("totalPage", PrePageUtil.pageCount(pageSize, totalCount));
            map.put("currentPage", pageIndex);
            map.put("viewTotal", pageSize);
            return RestResponse.success(map);
        } catch (Exception e) {
            logger.error("USER SERVICE ALL ERROR:" + e.getMessage());
            return RestResponse.failure(ResultCode.SYSTEM_ERROR);
        }
    }

    @Override
    public RestResponse create(UserRequest userRequest) {
        try {
            User user=new User();
            user.setUserPassword(DESUtil.encryptString(userRequest.getUserPassword()));
            user.setuId(RandomStringUtils.randomNumeric(10));
            if(StringUtils.isNotBlank(userRequest.getMailAddr())){
                user.setMailAddr(userRequest.getMailAddr());
            }
            if(StringUtils.isNotBlank(userRequest.getRealName())){
                user.setRealName(userRequest.getRealName());
            }
            if(StringUtils.isNotBlank(userRequest.getUserName())){
                user.setUserName(userRequest.getUserName());
            }
            if(StringUtils.isNotBlank(userRequest.getPhoneNum())){
                user.setPhoneNum(userRequest.getPhoneNum());
            }
            if(StringUtils.isNotBlank(userRequest.getRoleSerialNum())){
                user.setRoleSerialNum(userRequest.getRoleSerialNum());
            }
            userMapper.create(user);
            return RestResponse.success(null);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return RestResponse.failure(ResultCode.SYSTEM_ERROR);
        }
    }

    @Override
    public RestResponse update(String uId,UserRequest userRequest) {
        try {
            User user=userMapper.selectByUid(uId);
            if(null == user){
                return RestResponse.failure(ResultCode.USER_NOT_EXIST);
            }
            if(StringUtils.isNotBlank(userRequest.getMailAddr())){
                user.setMailAddr(userRequest.getMailAddr());
            }
            if(StringUtils.isNotBlank(userRequest.getRealName())){
                user.setRealName(userRequest.getRealName());
            }
            if(StringUtils.isNotBlank(userRequest.getUserName())){
                user.setUserName(userRequest.getUserName());
            }
            if(StringUtils.isNotBlank(userRequest.getRoleSerialNum()+"")){
                user.setRoleSerialNum(userRequest.getRoleSerialNum());
            }
            userMapper.update(user);
            return RestResponse.success(null);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return RestResponse.failure(ResultCode.SYSTEM_ERROR);
        }
    }

    @Override
    public RestResponse delete(String uId) {
        try {
            User user=userMapper.selectByUid(uId);
            if(null == user){
                return RestResponse.failure(ResultCode.USER_NOT_EXIST);
            }
            userMapper.delete(user.getuId());
            return RestResponse.success(null);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return RestResponse.failure(ResultCode.SYSTEM_ERROR);
        }
    }
}
