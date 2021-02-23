package com.h2t.study.dao;

import com.h2t.study.po.UserPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * UserMapper
 *
 * @author hetiantian
 * @version 1.0
 * @Date 2021/02/21 11:36
 */
@Mapper
public interface UserMapper {
    @Select("select * from users where id = #{id}")
    UserPO getById(@Param("id") String username);

    @Select("select * from users where id = #{id}")
    UserPO getByUsername(@Param("username") String username);

    @Update("update users set password = #{password} where id = #{id}")
    int updatePassword(UserPO userPO);
}
