package com.scrats.rent.admin.common.interceptor;

import com.alibaba.fastjson.JSON;
import com.scrats.rent.admin.common.APIRequest;
import com.scrats.rent.admin.common.annotation.IgnoreSecurity;
import com.scrats.rent.admin.common.exception.NotAuthorizedException;
import com.scrats.rent.admin.entity.Admin;
import com.scrats.rent.admin.service.AdminService;
import com.scrats.rent.admin.util.IOUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @Created with scrat.
 * @Description: ${DESCRIPTION}.
 * @Email: guosq@scrats.cn.
 * @Author: lol.
 * @Date: 2018/6/8 16:54.
 */
@Slf4j
public class AuthenticationInterceptor  implements HandlerInterceptor {

    @Autowired
    private AdminService adminService;

    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        String requestPath = httpServletRequest.getRequestURI();
        log.debug("Method: " + method.getName() + ", IgnoreSecurity: " + method.isAnnotationPresent(IgnoreSecurity.class));
        log.debug("requestPath: " + requestPath);

        if (method.isAnnotationPresent(IgnoreSecurity.class)) {
            return true;
        }

        String token = httpServletRequest.getHeader("tokenId");
        String adminId = httpServletRequest.getHeader("userId");
        log.debug("token: " + token);
        if (StringUtils.isBlank(token)) {
            throw new NotAuthorizedException("非法请求, 请登陆");
        }
        if (StringUtils.isBlank(adminId)) {
            throw new NotAuthorizedException("非法请求, 请登陆");
        }

        String json = IOUtil.getInputStreamAsText(httpServletRequest.getInputStream(),"UTF-8");
        APIRequest apiRequest = JSON.parseObject(json,APIRequest.class);
        if(null == apiRequest){
            apiRequest = new APIRequest();
        }
        Admin admin = adminService.checkLogin(Integer.parseInt(adminId), token);
        apiRequest.setAdmin(admin);
        apiRequest.setTokenId(token);

        httpServletRequest.setAttribute("apiRequest", apiRequest);
        return true;
    }

    /**
     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param e
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
