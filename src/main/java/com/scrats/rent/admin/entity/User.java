package com.scrats.rent.admin.entity;

import com.scrats.rent.admin.base.entity.BaseEntity;
import com.scrats.rent.admin.constant.SexType;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 * 用户实体类
 * Created by lol on 15/4/13.
 */
@Data
public class User extends BaseEntity {

    private static final long serialVersionUID = -6043513751393352184L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;//主键
    private String name;//姓名
    private String sex;//0-保密, 1-男, 2-女
    private String avatar;//头像URL
    private String qq;//qq
    private String wechat;//微信
    private String email;//email
    private String profession;//职业
    private String hometown;//籍贯
    private String address;//地址
    private Integer accountId;//账号Id

    private String idCard;//identification card 身份证号
    private String idCardPic;//身份证正面
    private String idCardPicBack;//身份证反面
    private String phone;//电话号码
    private Long checkTs;//实名认证时间戳

    @Transient
    private String hidePhone;
    @Transient
    private String hideIdCard;

    public User() {
        super();
    }

    public User(String name, SexType sexType, String phone, String idCard) {
        super();
        this.name = name;
        this.sex = sexType.getValue();
        this.phone = phone;
        this.idCard = idCard;
    }

    public String getSexName(){
        return SexType.fromValue(sex).getName();
    }

    public String getHidePhone(){
        return phone.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
    }

    public String getHideIdCard(){
        return idCard.replaceAll("(\\d{4})\\d{10}(\\w{4})","$1*****$2");
    }

}
