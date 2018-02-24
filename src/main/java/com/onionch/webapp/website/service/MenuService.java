package com.onionch.webapp.website.service;

import com.onionch.webapp.website.bean.response.RestResponse;
import com.onionch.webapp.website.bean.request.MenuRequest;

public interface MenuService {
    RestResponse create(MenuRequest menu);
    RestResponse update(String menuId,MenuRequest menu);
    RestResponse delete(String menuId);
    RestResponse listMenu();
}
