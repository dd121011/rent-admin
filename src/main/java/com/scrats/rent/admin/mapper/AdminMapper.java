package com.scrats.rent.admin.mapper;

import com.scrats.rent.admin.base.mapper.BaseMapper;
import com.scrats.rent.admin.entity.Admin;
import org.apache.ibatis.annotations.Select;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/5/24 00:12.
 */
public interface AdminMapper extends BaseMapper<Admin> {

    @Select("select * from admin " +
            "where 1=1 and username = #{username} and pwd = #{pwd}" +
            "limit 1")
    Admin login(Admin admin);

}
