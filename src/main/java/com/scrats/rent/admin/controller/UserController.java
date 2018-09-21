package com.scrats.rent.admin.controller;

import com.alibaba.fastjson.JSON;
import com.scrats.rent.admin.common.APIRequest;
import com.scrats.rent.admin.common.JsonResult;
import com.scrats.rent.admin.common.PageInfo;
import com.scrats.rent.admin.common.annotation.APIRequestControl;
import com.scrats.rent.admin.common.annotation.IgnoreSecurity;
import com.scrats.rent.admin.common.exception.BusinessException;
import com.scrats.rent.admin.constant.GlobalConst;
import com.scrats.rent.admin.constant.UserType;
import com.scrats.rent.admin.entity.*;
import com.scrats.rent.admin.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private DictionaryItermService dictionaryItermService;

    @IgnoreSecurity
    @GetMapping("/goUser")
    public String goUser(Integer userId, String tokenId, Map<String, Object> map) {

        Admin admin = adminService.checkLogin(userId, tokenId);
        List<DictionaryIterm> roles = dictionaryItermService.findListBy("dicCode", GlobalConst.ROLE_CODE);

        map.put("admin", admin);
        map.put("roles", roles);

        return "user/user_list";
    }

    @PostMapping("/list")
    @ResponseBody
    public String list(@APIRequestControl APIRequest apiRequest) {
        String roleCode = APIRequest.getParameterValue(apiRequest, "roleCode", null, String.class);
        User param = JSON.parseObject(JSON.toJSONString(apiRequest.getBody()),User.class);
        if(null == param){
            param = new User();
        }
        PageInfo<User> pageInfo = null;
        if(StringUtils.isEmpty(roleCode)){
            pageInfo = userService.getPagerByUser(apiRequest.getPage(), apiRequest.getRows(), param);
        }else{
            pageInfo = userService.getPagerWithRoleByUser(apiRequest.getPage(), apiRequest.getRows(), param, roleCode);
        }

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
            String roleCode = APIRequest.getParameterValue(apiRequest, "roleCode", null, String.class);
            UserType userType = UserType.fromValue(roleCode);
            if(null == userType){
                throw new BusinessException("请求参数有误");
            }
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

            UserRole userRole = new UserRole(userType, user.getUserId());
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

    @GetMapping("/realConfirm/{userId}")
    @ResponseBody
    public JsonResult realConfirm(@PathVariable Integer userId){
        User user = userService.selectByPrimaryKey(userId);
        if(StringUtils.isEmpty(user.getIdCardPic() ) || StringUtils.isEmpty(user.getIdCardPicBack())){
            throw new BusinessException("身份证图片不完整, 认证不通过!");
        }
        user.setCheckTs(System.currentTimeMillis());
        userService.updateByPrimaryKeySelective(user);
        return new JsonResult();
    }
}
