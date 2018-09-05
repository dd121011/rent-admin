package com.scrats.rent.admin.service;

import com.scrats.rent.admin.base.service.BaseService;
import com.scrats.rent.admin.constant.UserType;
import com.scrats.rent.admin.entity.UserRole;
import com.scrats.rent.admin.mapper.UserRoleMapper;

import java.util.List;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/6/6 22:33.
 */
public interface UserRoleService extends BaseService<UserRole, UserRoleMapper> {

    List<UserRole> getListByUserRole(UserRole userRole);

    boolean noExistRoleWithUserId(UserType userType, Integer userId);
}
