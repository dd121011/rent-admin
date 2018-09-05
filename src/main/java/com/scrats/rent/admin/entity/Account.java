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
public class Account extends BaseEntity {

    private static final long serialVersionUID = 7466923808730866199L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(generator = "JDBC")
    private Integer accountId;//主键
    private String username;//用户名
    private String pwd;//密码
    private String phone;//手机号码

    public Account() {
        super();
    }

    public Account(String username, String pwd) {
        super();
        this.username = username;
        this.pwd = pwd;
    }

    public Account(String username, String pwd, String phone) {
        this.username = username;
        this.pwd = pwd;
        this.phone = phone;
    }
}
