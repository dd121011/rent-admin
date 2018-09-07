package com.scrats.rent.admin.controller;

import com.alibaba.fastjson.JSON;
import com.scrats.rent.admin.common.APIRequest;
import com.scrats.rent.admin.common.JsonResult;
import com.scrats.rent.admin.common.PageInfo;
import com.scrats.rent.admin.common.annotation.APIRequestControl;
import com.scrats.rent.admin.common.annotation.IgnoreSecurity;
import com.scrats.rent.admin.entity.Admin;
import com.scrats.rent.admin.entity.User;
import com.scrats.rent.admin.service.AdminService;
import com.scrats.rent.admin.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
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
@RequestMapping("/landlord")
public class LandlordController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;

    @IgnoreSecurity
    @GetMapping("/goLandlord")
    public String goLandlord(Integer userId, String tokenId, Map<String, Object> map) {

        Admin admin = adminService.checkLogin(userId, tokenId);

        map.put("admin", admin);

        return "landlord/landlord_list";
    }

    @PostMapping("/list")
    @ResponseBody
    public String list(@APIRequestControl APIRequest apiRequest) {
        User param = JSON.parseObject(JSON.toJSONString(apiRequest.getBody()),User.class);
        if(null == param){
            param = new User();
        }
        PageInfo<User> pageInfo = userService.getPagerByUser(apiRequest.getPage(), apiRequest.getRows(), param);

        return JSON.toJSONString(new JsonResult<List>(pageInfo.getList(), (int) pageInfo.getTotal()));
    }
}
