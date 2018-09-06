package com.scrats.rent.admin.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.scrats.rent.admin.base.service.BaseServiceImpl;
import com.scrats.rent.admin.base.service.RedisService;
import com.scrats.rent.admin.common.JsonResult;
import com.scrats.rent.admin.common.exception.BusinessException;
import com.scrats.rent.admin.common.exception.NotAuthorizedException;
import com.scrats.rent.admin.constant.GlobalConst;
import com.scrats.rent.admin.entity.Admin;
import com.scrats.rent.admin.mapper.AdminMapper;
import com.scrats.rent.admin.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/6/6 22:34.
 */
@Slf4j
@Service
public class AdminServiceImpl extends BaseServiceImpl<Admin, AdminMapper> implements AdminService {

    @Autowired
    private RedisService redisService;

    @Override
    public JsonResult login(String username, String pwd) {

        Admin result = dao.login(new Admin(username, pwd));
        if(null != result){
            String tokenId = UUID.randomUUID().toString().replace("-","");
            redisService.set(tokenId,result, GlobalConst.ACCESS_TOKEN_EXPIRE);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("tokenId", tokenId);
            jsonObject.put("userId", result.getAdminId());
            return new JsonResult<JSONObject>(jsonObject);
        }
        Admin sel = new Admin();
        sel.setUsername(username);
        result = dao.login(sel);
        if(null != result){
            return new JsonResult("账号不存在");
        }
        return new JsonResult("账号密码错误");

    }

    @Override
    public Admin checkLogin(Integer adminId, String tokenId) {
        Admin admin = (Admin)redisService.get(tokenId);
        if(null == admin){
            throw new NotAuthorizedException("非法请求, 请登陆");
        }
        if(admin.getAdminId() - adminId != 0){
            throw new BusinessException("请求参数错误, 请检查");
        }
        return admin;
    }
}
