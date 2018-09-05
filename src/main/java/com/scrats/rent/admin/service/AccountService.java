package com.scrats.rent.admin.service;

import com.scrats.rent.admin.base.service.BaseService;
import com.scrats.rent.admin.common.JsonResult;
import com.scrats.rent.admin.entity.Account;
import com.scrats.rent.admin.mapper.AccountMapper;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/5/24 00:16.
 */
public interface AccountService extends BaseService<Account, AccountMapper> {

    JsonResult login(String username, String pwd);

}
