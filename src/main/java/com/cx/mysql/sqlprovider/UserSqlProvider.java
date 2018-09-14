package com.cx.mysql.sqlprovider;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

/**
 *
 */
public class UserSqlProvider {

    public String getUser(@Param("username") String username,@Param("sex") String sex){

        return new SQL() {{
            SELECT("*");
            FROM("sys_user_test");
            if (username != null) {
                WHERE("user_name = #{username}");
            }
            if (sex != null) {
                WHERE("sex = #{sex}");
            }
        }
        }.toString();


    }
}
