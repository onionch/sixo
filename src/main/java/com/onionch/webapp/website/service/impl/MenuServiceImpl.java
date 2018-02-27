package com.onionch.webapp.website.service.impl;

import com.mysql.jdbc.StringUtils;
import com.onionch.webapp.website.bean.Menu;
import com.onionch.webapp.website.bean.request.MenuListRequest;
import com.onionch.webapp.website.bean.response.RestResponse;
import com.onionch.webapp.website.bean.enums.ResultCode;
import com.onionch.webapp.website.bean.request.MenuRequest;
import com.onionch.webapp.website.mapper.MenuMapper;
import com.onionch.webapp.website.service.MenuService;
import com.onionch.webapp.website.util.EncryptUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("menuServiceImpl")
public class MenuServiceImpl implements MenuService {

    private static final Logger logger = Logger.getLogger(MenuServiceImpl.class);

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public RestResponse create(MenuRequest menuRequest) {
        try {
            Menu menu = new Menu();
            menu.setMenuName(menuRequest.getMenuName());
            menu.setAccess(menuRequest.getAccess());
            menu.setUrl(menuRequest.getUrl());
            menu.setSerialNum(EncryptUtil.generate32(menu.getMenuName() + new Date().getTime()));
            menuMapper.create(menu);
            return RestResponse.success(null);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return RestResponse.failure(ResultCode.SYSTEM_ERROR);
        }
    }

    @Override
    public RestResponse update(String menuId, MenuRequest menuRequest) {
        try {
            Menu menu = menuMapper.findBySerialNumber(menuId);
            if(menu == null){
                return RestResponse.failure(ResultCode.MENU_NOT_EXIST);
            }
            if(!StringUtils.isNullOrEmpty(menuRequest.getMenuName())){
                menu.setMenuName(menuRequest.getMenuName());
            }
            if(!StringUtils.isNullOrEmpty(menuRequest.getAccess()+"")){
                menu.setAccess(menuRequest.getAccess());
            }
            if(!StringUtils.isNullOrEmpty(menuRequest.getUrl())){
                menu.setUrl(menuRequest.getUrl());
            }
            menuMapper.update(menu);
            return RestResponse.success(null);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return RestResponse.failure(ResultCode.SYSTEM_ERROR);
        }
    }

    @Override
    public RestResponse delete(String id) {
        try {
            Menu menu = menuMapper.findBySerialNumber(id);
            if(menu == null){
                return RestResponse.failure(ResultCode.MENU_NOT_EXIST);
            }
            menuMapper.delete(id);
            return RestResponse.success(null);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return RestResponse.failure(ResultCode.SYSTEM_ERROR);
        }
    }

    @Override
    public RestResponse listMenu() {
        try {
            return RestResponse.success(menuMapper.listAll());
        }catch (Exception e){
            logger.error(e.getMessage());
            return RestResponse.failure(ResultCode.SYSTEM_ERROR);
        }
    }

    @Override
    public RestResponse listMenuByUserName(String userName) {
        try {
            return RestResponse.success(menuMapper.listMenuByUserName(userName));
        }catch (Exception e){
            logger.error(e.getMessage());
            return RestResponse.failure(ResultCode.SYSTEM_ERROR);
        }
    }
}
