package com.scrats.rent.admin.service.impl;

import com.scrats.rent.admin.base.service.BaseServiceImpl;
import com.scrats.rent.admin.constant.UserType;
import com.scrats.rent.admin.entity.UserRole;
import com.scrats.rent.admin.mapper.UserRoleMapper;
import com.scrats.rent.admin.service.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/6/6 22:34.
 */
@Slf4j
@Service
public class UserRoleServiceImpl extends BaseServiceImpl<UserRole, UserRoleMapper> implements UserRoleService {

    @Override
    public List<UserRole> getListByUserRole(UserRole userRole) {
        return dao.getListByUserRole(userRole);
    }

    @Override
    public boolean noExistRoleWithUserId(UserType userType, Integer userId) {
        UserRole param = new UserRole(userType, userId);
        List<UserRole> result = dao.getListByUserRole(param);
        if(result.size() > 0){
            return false;
        }
        return true;
    }
}
