package com.scrats.rent.admin.service;

import com.scrats.rent.admin.base.service.BaseService;
import com.scrats.rent.admin.common.JsonResult;
import com.scrats.rent.admin.entity.Admin;
import com.scrats.rent.admin.mapper.AdminMapper;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/6/6 22:33.
 */
public interface AdminService extends BaseService<Admin, AdminMapper> {

    JsonResult login(String username, String pwd);
}
