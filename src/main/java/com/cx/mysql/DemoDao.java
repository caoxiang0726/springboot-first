package com.cx.mysql;

import com.cx.mysql.po.SysUserPo;
import com.cx.mysql.sqlprovider.UserSqlProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

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

    @Delete("delete from sys_user_test WHERE id = #{id}")
    int delById(SysUserPo po);

    @SelectProvider(type = UserSqlProvider.class,method = "getUser")
    List<SysUserPo> getUser(@Param("username") String username,@Param("sex") String sex);






}
