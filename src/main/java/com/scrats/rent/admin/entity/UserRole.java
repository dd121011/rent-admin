package com.scrats.rent.admin.entity;

import com.scrats.rent.admin.base.entity.BaseEntity;
import com.scrats.rent.admin.constant.UserType;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 * 用户角色实体类
 * Created by lol on 2018/8/14.
 */
@Data
public class UserRole extends BaseEntity {

    private static final long serialVersionUID = -4653309083382591351L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userRoleId;//主键
    private String roleCode;//角色code
    private Integer userId;//用户id

    @Transient
    private User user;

    public UserRole() {
    }

    public UserRole(UserType userType, Integer userId) {
        this.roleCode = userType.getValue();
        this.userId = userId;
    }
}
