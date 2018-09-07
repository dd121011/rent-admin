package com.scrats.rent.admin.mapper;

import com.scrats.rent.admin.base.mapper.BaseMapper;
import com.scrats.rent.admin.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created with scrat.
 * Description: ${DESCRIPTION}.
 * Email:    guosq@scrats.cn.
 * Author:   lol.
 * Date:     2018/6/6 22:32.
 */
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from user where 1=1 and account_id = #{accountId} limit 1")
    User getUserByAccountId(Integer accountId);

    @Select("<script>select u.* " +
            "from user u " +
            "where 1=1 " +
            "<if test='user.userId != null'>and u.user_id = #{user.userId}</if>" +
            "<if test='user.name != null and user.name !=\"\"'>and u.name like concat('%', #{user.name}, '%')</if>" +
            "<if test='user.phone != null and user.phone !=\"\"'>and u.phone like concat('%', #{user.phone}, '%')</if>" +
            "<if test='user.qq != null and user.qq !=\"\"'>and u.qq like concat('%', #{user.qq}, '%')</if>" +
            "<if test='user.wechat != null and user.wechat !=\"\"'>and u.wechat like concat('%', #{user.wechat}, '%')</if>" +
            "<if test='user.email != null and user.email !=\"\"'>and u.email like concat('%', #{user.email}, '%')</if>" +
            "<if test='user.profession != null and user.profession !=\"\"'>and u.profession like concat('%', #{user.profession}, '%')</if>" +
            "<if test='user.hometown != null and user.hometown !=\"\"'>and u.hometown like concat('%', #{user.hometown}, '%')</if>" +
            "<if test='user.sex != null and user.sex !=\"\"'>and u.sex = #{user.sex}</if>" +
            "<if test='user.deleteTs != null and user.deleteTs > 0'>and u.delete_ts > 0 and check_ts > 0</if>" +
            "<if test='user.deleteTs == null or user.deleteTs == 0'>and (check_ts = 0 or (check_ts > 0 and delete_ts = 0))</if>" +
            "<if test='user.checkTs != null or user.checkTs == 0'>and checkTs = 0</if>" +
            "<if test='user.checkTs != null or user.checkTs > 0'>and checkTs > 0</if>" +
            "</script>")
    List<User> getListByUser(@Param("user") User user);

    @Update("<script>update user u set u.delete_ts = #{deleteTs} where 1=1 and u.user_id in <foreach item='item' index='index' collection='ids' open='(' separator=',' close=')'> #{item} </foreach></script>")
    int deleteUserByIds(@Param("deleteTs") long deleteTs, @Param("ids") Integer... ids);
}
