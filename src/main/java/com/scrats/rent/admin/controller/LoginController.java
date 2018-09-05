package com.scrats.rent.admin.controller;

import com.scrats.rent.admin.common.JsonResult;
import com.scrats.rent.admin.common.annotation.IgnoreSecurity;
import com.scrats.rent.admin.entity.Admin;
import com.scrats.rent.admin.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/5/27 22:37.
 */
@Slf4j
@Controller
public class LoginController {

    @Autowired
    private AdminService adminService;

    @IgnoreSecurity
    @PostMapping("/login")
    @ResponseBody
    public JsonResult login(@RequestBody Admin admin) {

        return adminService.login(admin.getUsername(), admin.getPwd());
    }

}
