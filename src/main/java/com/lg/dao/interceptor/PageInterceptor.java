package com.lg.dao.interceptor;

import com.lg.bean.BaseBean;
import com.lg.bean.Page;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

/**
 * CreateBy MIO
 * On 2017/11/22/0022
 */
@Intercepts({@Signature(type=StatementHandler.class,method="prepare",args={Connection.class})})
public class PageInterceptor implements Interceptor{

    /**
     * 拦截器要执行的方法
     */
    public Object intercept(Invocation arg0) throws Throwable {
        StatementHandler statementHandler = (StatementHandler)arg0.getTarget();
        MetaObject metaObject = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY, SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY,new DefaultReflectorFactory());
        MappedStatement mappedStatement = (MappedStatement)metaObject.getValue("delegate.mappedStatement");
        String id = mappedStatement.getId();
        if(id.endsWith("ByPage")) {
            BoundSql boundSql = statementHandler.getBoundSql();
            String sql = boundSql.getSql();
            String countSql = "select count(*) from(" + sql + ")t";
            Connection conn = (Connection)arg0.getArgs()[0];
            PreparedStatement statement = conn.prepareStatement(countSql);
            ParameterHandler parameterHandler = (ParameterHandler)metaObject.getValue("delegate.parameterHandler");
            parameterHandler.setParameters(statement);
            ResultSet rs = statement.executeQuery();
            BaseBean bean = (BaseBean)boundSql.getParameterObject();
            Page page = bean.getPage();
            if(rs.next()) {
                page.setTotalNumber(rs.getInt(1));
            }
            String pageSql = sql + " limit " + (page.getCurrentPage() - 1) * page.getPageNumber() + "," + page.getPageNumber();
            metaObject.setValue("delegate.boundSql.sql", pageSql);
        }
        return arg0.proceed();
    }

    /**
     * 拦截器要拦截的对象
     * @param target
     * @return
     */
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    /**
     * 设置初始化的属性值
     * @param properties
     */
    public void setProperties(Properties properties) {

    }
}
