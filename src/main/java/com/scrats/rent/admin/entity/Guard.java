package com.scrats.rent.admin.entity;

import com.scrats.rent.admin.base.entity.BaseEntity;
import lombok.Data;

/**
 * 巡管员实体类
 * Created by lol on 15/4/23.
 */
@Data
public class Guard extends BaseEntity {

    private static final long serialVersionUID = -403878679048886997L;

    private int id;//主键
    private Boolean issuper;//超级寻管员

    private int userId;//一个管巡管员对应一个账号

}
