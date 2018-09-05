package com.scrats.rent.admin.controller;

import com.scrats.rent.admin.base.service.RedisService;
import com.scrats.rent.admin.common.annotation.IgnoreSecurity;
import com.scrats.rent.admin.common.exception.BusinessException;
import com.scrats.rent.admin.common.exception.NotAuthorizedException;
import com.scrats.rent.admin.entity.Admin;
import com.scrats.rent.admin.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

/**
 * @Created with scrat.
 * @Description: ${DESCRIPTION}.
 * @Email: guosq@scrats.cn.
 * @Author: lol.
 * @Date: 2018/9/5 22:33.
 */
@Slf4j
@Controller
public class AdminController {
    @Autowired
    private RedisService redisService;

    @Autowired
    private AdminService adminService;

    @IgnoreSecurity
    @GetMapping("/goHome/{adminId}")
    public String goHome(@PathVariable Integer adminId, String tokenId, Map<String, Object> map) {

        Admin admin = (Admin)redisService.get(tokenId);
        if(null == admin){
            throw new NotAuthorizedException("非法请求, 请登陆");
        }
        if(admin.getAdminId() - adminId != 0){
            throw new BusinessException("请求参数错误, 请检查");
        }

        map.put("admin","admin");

        return "home";
    }
}
