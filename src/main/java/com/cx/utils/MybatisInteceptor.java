package com.cx.utils;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.stereotype.Component;

import java.util.Properties;


/**
 * Created by caoxiang on 2018/4/11.
 */
@Intercepts({@Signature(type = Executor.class,
        method = "query",args = {MappedStatement.class,Object.class})})
//@Componentent
public class MybatisInteceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        /*StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        String selectId = mappedStatement.getId();*/
//        metaObject.metaObject

        Object result = invocation.proceed();
        System.out.println("--------------mybaits  intercept----------");

        return result;
    }

    @Override
    public Object plugin(Object o) {
        return null;
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
