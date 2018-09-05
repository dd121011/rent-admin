package com.scrats.rent.admin.common.filter;

import com.scrats.rent.admin.common.exception.NotAuthorizedException;
import com.scrats.rent.admin.util.CusAccessObjectUtil;
import com.scrats.rent.admin.util.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Created with jointstarc.
 * @Email: 262297088@qq.com
 * @Description:
 * @User: lol.
 * @Date: 2018/1/3 15:21.
 */
public class CsrfFilter implements Filter {

    private final static Logger logger = LoggerFactory.getLogger(CsrfFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("====>CsrfFilter启动<====");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestAdress = CusAccessObjectUtil.getIpAddress(request);
        String edcDomain = PropertiesUtil.getProperty("system.properties","edc.domain");
//        throw new NotAuthorizedException("非法请求");
        if(edcDomain.indexOf(requestAdress) > -1){
            filterChain.doFilter(request, response);
        }else{
            throw new NotAuthorizedException("非法请求");
        }
    }

    @Override
    public void destroy() {

    }
}
