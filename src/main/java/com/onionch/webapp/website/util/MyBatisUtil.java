package com.onionch.webapp.website.util;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

/**
 * Created by onionch on 6/5/17.
 */
public class MyBatisUtil {

    private static SqlSessionFactory factory;

    private static ThreadLocal<SqlSession> local = new ThreadLocal<SqlSession>();

    private static Logger logger = Logger.getLogger(MyBatisUtil.class);

    static{
        try {
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();

            factory = builder.build(MyBatisUtil.class.getClassLoader()

                    .getResourceAsStream("mybatis-config.xml")
            );
        }
        catch (Exception e)
        {
            logger.error("MyBatisUtil初始化失败",e);
            throw new ExceptionInInitializerError("MyBatisUtil初始化失败");
        }
    }

    public static SqlSession getSession()
    {

        SqlSession session = local.get();

        if(session == null)
        {
            session = factory.openSession();
            local.set(session);;
        }
        return session;
    }

    public static void close()
    {
        getSession().close();
        local.remove();
    }

    public static void close(SqlSession session)
    {
        if(session != null)
        {
            session.close();
        }
    }

}
