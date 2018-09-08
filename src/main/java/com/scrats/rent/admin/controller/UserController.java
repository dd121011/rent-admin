package com.scrats.rent.admin.controller;

import com.alibaba.fastjson.JSON;
import com.scrats.rent.admin.common.APIRequest;
import com.scrats.rent.admin.common.JsonResult;
import com.scrats.rent.admin.common.PageInfo;
import com.scrats.rent.admin.common.annotation.APIRequestControl;
import com.scrats.rent.admin.common.annotation.IgnoreSecurity;
import com.scrats.rent.admin.constant.UserType;
import com.scrats.rent.admin.entity.*;
import com.scrats.rent.admin.service.AccountService;
import com.scrats.rent.admin.service.AdminService;
import com.scrats.rent.admin.service.UserRoleService;
import com.scrats.rent.admin.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * @Created with scrat.
 * @Description: ${DESCRIPTION}.
 * @Email: guosq@scrats.cn.
 * @Author: lol.
 * @Date: 2018/9/5 22:33.
 */
@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private AccountService accountService;

    @IgnoreSecurity
    @GetMapping("/goUser")
    public String goLandlord(Integer userId, String tokenId, Map<String, Object> map) {

        Admin admin = adminService.checkLogin(userId, tokenId);

        map.put("admin", admin);

        return "user/user_list";
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

    @PostMapping("/edit")
    @ResponseBody
    @Transactional
    public JsonResult edit(@APIRequestControl APIRequest apiRequest) {
        User user = JSON.parseObject(JSON.toJSONString(apiRequest.getBody()),User.class);
        long createTs = System.currentTimeMillis();
        if(null != user.getUserId()){
            user.setUpdateTs(createTs);
            userService.updateByPrimaryKeySelective(user);
        }else{
            if(userService.exists("phone",user.getPhone())){
                return new JsonResult<>("创建失败,该手机号已存在");
            }
            if(userService.exists("idCard",user.getIdCard())){
                return new JsonResult<>("创建失败,该身份证号存在");
            }

            Account account = new Account(user.getPhone(), user.getPhone(), user.getPhone());
            account.setCreateTs(createTs);
            accountService.insertSelective(account);

            user.setAccountId(account.getAccountId());
            user.setCreateTs(createTs);
            userService.insertSelective(user);

            UserRole userRole = new UserRole(UserType.landlord, user.getUserId());
            userRole.setCreateTs(createTs);
            userRoleService.insertSelective(userRole);
        }

        return new JsonResult<>();
    }

    @PostMapping("/delete")
    @ResponseBody
    public JsonResult delete(@APIRequestControl APIRequest apiRequest, Integer... ids){
        userService.deleteUserByIds(ids);
        return new JsonResult();
    }
}
