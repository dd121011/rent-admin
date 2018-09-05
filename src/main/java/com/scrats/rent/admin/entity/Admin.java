package com.scrats.rent.admin.entity;

import com.scrats.rent.admin.base.entity.BaseEntity;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/5/13 16:33.
 */
@Data
public class Admin extends BaseEntity {

    private static final long serialVersionUID = -6214789611403495734L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer adminId;//主键
    private String username;//用户名
    private String pwd;//密码
    private String name;//姓名
    private String phone;//手机号码
    private String flag;//0-管理员, 1-超级管理员

    public Admin() {
        super();
    }

    public Admin(String username, String pwd) {
        super();
        this.username = username;
        this.pwd = pwd;
    }

    public Admin(String username, String pwd, String name, String phone) {
        this.username = username;
        this.pwd = pwd;
        this.name = name;
        this.phone = phone;
    }
}
