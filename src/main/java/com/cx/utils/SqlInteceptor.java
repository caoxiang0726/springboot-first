package com.cx.utils;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * Created by caoxiang on 2018/4/11.
 */
@Intercepts({@Signature(type = Executor.class,method = "query",
        args = {MappedStatement.class,Object.class, RowBounds.class, ResultHandler.class})})
@Component
public class SqlInteceptor  implements Interceptor{
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        Object parameter = null;
        if (invocation.getArgs().length>1) {
            parameter = invocation.getArgs()[1];
        }
        String sqlId = mappedStatement.getId();
        BoundSql boundSql = mappedStatement.getBoundSql(parameter);
        Configuration configuration = mappedStatement.getConfiguration();
        String sql = boundSql.getSql();
        //-----------sql:select id, name as name, money as money from springboot_fast_account where id = ?
        System.out.println("-----------sql:"+sql);

        Object proceed = invocation.proceed();

        return proceed;
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o,this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
