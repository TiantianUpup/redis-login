package com.h2t.study.dao;

import com.h2t.study.po.UserPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * UserMapper
 *
 * @author hetiantian
 * @version 1.0
 * @Date 2021/02/21 11:36
 */
@Mapper
public interface UserMapper {
    @Select("select * from users where username = #{username}")
    UserPO getByUsername(@Param("username") String username);
}
