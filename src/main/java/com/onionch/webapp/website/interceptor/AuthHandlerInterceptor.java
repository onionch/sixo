package com.onionch.webapp.website.interceptor;

import com.onionch.webapp.website.bean.*;
import com.onionch.webapp.website.service.PrivilegeService;
import com.onionch.webapp.website.service.ResourceService;
import com.onionch.webapp.website.service.RoleService;
import com.onionch.webapp.website.service.TokenService;
import com.onionch.webapp.website.staticless.TokenStatic;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthHandlerInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private PrivilegeService privilegeService;

    @Autowired
    private RoleService roleService;

    /**
     * Handler执行完成之后调用这个方法
     */
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception exc)
            throws Exception {

    }

    /**
     * Handler执行之后，ModelAndView返回之前调用这个方法
     */
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
    }

    /**
     * Handler执行之前调用这个方法
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        //获取请求的URL
        String url = request.getRequestURI();
        String[] urls = url.split("/");
        String resource = "";
        if (urls.length >= 3) {
            resource = urls[3];
        }
        if (resource.indexOf("token") >= 0) {
            return true;
        }

        String token = request.getHeader("Token");
        if (StringUtils.isEmpty(token)) {
            response.getWriter().print(JSONObject.fromObject(RestResponse.response(500, "\\u6ca1\\u6709TOKEN", "no token")));
            return false;
        }

        Token hasToken = tokenService.selectByToken(token);
        if (null == hasToken) {
            response.getWriter().print(JSONObject.fromObject(RestResponse.response(500, "\\u6ca1\\u6709TOKEN", "error token")));
            return false;
        }

        Role hasRole=roleService.findRoleById(hasToken.getRoleId());
        if(null == hasRole){
            response.getWriter().print(JSONObject.fromObject(RestResponse.response(500, "\\u6ca1\\u6709\\u8d44\\u6e90", "no role")));
            return false;
        }

        if(hasRole.getRoleName().equals("admin")){
            return true;
        }

        Resource hasResource = resourceService.findByName(resource);
        if (null == hasResource) {
            response.getWriter().print(JSONObject.fromObject(RestResponse.response(500, "\\u6ca1\\u6709\\u8d44\\u6e90", "no resource")));
            return false;
        }

        Privilege privilege = privilegeService.findByRoleId(hasToken.getRoleId(), hasResource.getId());
        if (null == privilege) {
            response.getWriter().print(JSONObject.fromObject(RestResponse.response(500, "\\u6ca1\\u6709\\u64cd\\u4f5c\\u6743\\u9650", "no privilege")));
            return false;
        }

        String method = request.getMethod();
        if (privilege.getOperator().indexOf(method.toLowerCase()) == -1) {
            response.getWriter().print(JSONObject.fromObject(RestResponse.response(500, "\\u6ca1\\u6709\\"+method+"\\u6743\\u9650", "no method")));
            return false;
        }
        return true;
    }
}
