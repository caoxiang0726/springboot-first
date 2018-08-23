package com.cx;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by caoxiang on 2018/3/18.
 */
@Mapper
public interface HelloMapper {

    @Insert("insert into springboot_fast_account(name, money) values(#{name}, #{money})")
    Integer add (@Param("name")String name,@Param("money")double money);

    @Select("select id, name as name, money as money from springboot_fast_account where id = #{id}")
    Account findAccount(@Param("id") int id);
}
