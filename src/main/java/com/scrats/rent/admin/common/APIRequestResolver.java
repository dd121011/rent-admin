package com.scrats.rent.admin.common;

import com.scrats.rent.admin.common.annotation.APIRequestControl;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

/**
 * @Created with scrat.
 * @Description: 增加方法注入，将含有 @APIRequestControl 注解的方法参数注入当前登录用户.
 * @Email: guosq@scrats.cn.
 * @Author: lol.
 * @Date: 2018/6/8 17:19.
 */
public class APIRequestResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().isAssignableFrom(APIRequest.class) && methodParameter.hasParameterAnnotation(APIRequestControl.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        APIRequest apiRequest = (APIRequest) nativeWebRequest.getAttribute("apiRequest", RequestAttributes.SCOPE_REQUEST);
        if (apiRequest != null) {
            return apiRequest;
        }
        throw new MissingServletRequestPartException("@APIRequestControl");
    }
}
