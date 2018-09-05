package com.scrats.rent.admin.entity;

import com.scrats.rent.admin.base.entity.BaseEntity;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.IdentityDialect;

import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.List;

/**
 * 房租实体类
 * Created by lol on 15/4/23.
 */
@Data
public class Rent extends BaseEntity {

    private static final long serialVersionUID = 6961570224294320920L;

    @Id
    @KeySql(dialect = IdentityDialect.MYSQL)
    private Integer rentId;//主键
    private String rentNo;//房租收据单号,32位
    private String rentMonth;//房租月份, eg 201806
    private Integer fee;//总费用
    private Integer count;//折扣费用
    private Integer realFee;//实际费用
    private Long payTs;//支付时间戳，13位
    private String payNo;//支付订单号
    private String channel;//支付渠道，99-未支付;0-线下支付;1-微信支付;2-支付宝支付
    private Integer roomId;//房间id,一个房租对应一个roomId
    private Integer buildingId;//房间id,一个房租对应一个roomId
    private Integer barginId;//合同Id

    @Transient
    private String roomNo;//房号
    @Transient
    private Room room;//房号
    @Transient
    private List<RentIterm> rentItermList;//房号
    @Transient
    private Bargin bargin;
}
