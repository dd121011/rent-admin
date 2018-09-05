package com.scrats.rent.admin.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.scrats.rent.admin.base.service.BaseServiceImpl;
import com.scrats.rent.admin.base.service.RedisService;
import com.scrats.rent.admin.common.JsonResult;
import com.scrats.rent.admin.constant.GlobalConst;
import com.scrats.rent.admin.entity.Account;
import com.scrats.rent.admin.entity.User;
import com.scrats.rent.admin.mapper.AccountMapper;
import com.scrats.rent.admin.service.AccountService;
import com.scrats.rent.admin.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/5/24 00:17.
 */
@Service
@Slf4j
public class AccountServiceImpl extends BaseServiceImpl<Account, AccountMapper> implements AccountService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private UserService userService;

    @Override
    public JsonResult login(String username, String pwd) {

        Account result = dao.login(new Account(username, pwd));
        if(null != result){
            User user = userService.getUserByAccountId(result.getAccountId());
            String tokenId = UUID.randomUUID().toString().replace("-","");
            redisService.set(tokenId,user, GlobalConst.ACCESS_TOKEN_EXPIRE);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("tokenId", tokenId);
            jsonObject.put("userId", user.getUserId());
            return new JsonResult<JSONObject>(jsonObject);
        }
        Account sel = new Account();
        sel.setUsername(username);
        result = dao.login(sel);
        if(null != result){
            return new JsonResult("账号不存在");
        }
        return new JsonResult("账号密码错误");

    }

}
