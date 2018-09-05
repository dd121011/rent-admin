package com.scrats.rent.admin.entity;

import com.scrats.rent.admin.base.entity.BaseEntity;
import com.scrats.rent.admin.constant.ChannelType;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.List;

/**
 * 押金实体类
 * Created by lol on 15/4/23.
 */
@Data
public class Deposit extends BaseEntity {

    private static final long serialVersionUID = 1745302216883579467L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer depositId;//主键
    private String depositNo;//押金编号,32位
    private Integer fee;//总费用
    private Long payTs;//支付时间戳，13位
    private String payNo;//支付订单号
    private String channel;//支付渠道，99-未支付;0-线下支付;1-微信支付;2-支付宝支付
    private Integer roomId;//房间id,一个押金对应一个roomId,一个roomId可能对应多个押金Id
    private Integer userId;//租客的userId
    private Integer buildingId;//buildingId
    private Integer barginId;//合同Id

    @Transient
    private List<DepositIterm> depositItermList;

    public String getChannelName(){
        return ChannelType.fromValue(channel).getName();
    }
}
