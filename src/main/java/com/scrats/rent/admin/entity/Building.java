package com.scrats.rent.admin.entity;

import com.scrats.rent.admin.base.entity.BaseEntity;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.IdentityDialect;

import javax.persistence.Id;
import java.util.List;

/**
 * 房子实体类,一个房东可能有多个房子，一个房子对应多个房间
 * Created by lol on 15/4/23.
 */
@Data
public class Building extends BaseEntity {

    private static final long serialVersionUID = -8526366061711633128L;

    @Id
    @KeySql(dialect = IdentityDialect.MYSQL)
    private Integer buildingId;//主键
    private String name;//房子名称
    private Integer rooms;//总的房间数
    private Integer roomAble;//可用房间数,通过总的房间数和可用房间数可以计算出出租房间数
    private String description;//描述
    private String address;//地址

    private List<Attachment> attachmentList;//一个房子对应多个attachment

}
