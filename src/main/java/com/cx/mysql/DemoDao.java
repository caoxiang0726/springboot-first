package com.cx.mysql;

import com.cx.mysql.po.SysUserPo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

/**
 *
 */
@Mapper
public interface DemoDao {
    /**
     * INSERT INTO sys_user_test(user_name, birthday, sex) VALUES ('ZHANGSAN',now(),'M')
     */
    @Insert("INSERT INTO sys_user_test(user_name, birthday, sex) VALUES (#{userName},#{birthday},#{sex})")
    @Options(useGeneratedKeys = true,keyColumn = "id",keyProperty = "id")
    int addUser(SysUserPo po);



}
