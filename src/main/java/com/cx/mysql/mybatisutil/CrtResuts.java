package com.cx.mysql.mybatisutil;

import com.cx.mysql.po.SysUserPo;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import java.lang.reflect.Field;

/**
 * Created by caoxiang on 2018/9/14.
 */
public class CrtResuts {
    public static void main(String[] args) {
        String resultsStr = getResultsStr(SysUserPo.class);
        System.out.println(resultsStr);
    }

    /**
     * 1.用于获取结果集的映射关系
     */
    public static String getResultsStr(Class origin) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("@Results({\n");
        for (Field field : origin.getDeclaredFields()) {
            String property = field.getName();
            //映射关系：对象属性(驼峰)->数据库字段(下划线)
            String column = new PropertyNamingStrategy.SnakeCaseStrategy().
                    translate(field.getName()).toUpperCase();
            stringBuilder.
                    append(String.format("@Result(property = \"%s\", column = \"%s\"),\n", property, column));
        }
        stringBuilder.append("})");
        return stringBuilder.toString();
    }
}
