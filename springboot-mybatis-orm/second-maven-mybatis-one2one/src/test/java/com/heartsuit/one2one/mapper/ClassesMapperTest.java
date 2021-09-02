package com.heartsuit.one2one.mapper;

import com.heartsuit.one2one.model.Classes;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

/**
 * @Author Heartsuit
 * @Date 2021-09-02
 */
public class ClassesMapperTest {
    private static SqlSessionFactory sqlSessionFactory;

    @Before
    public void initialize() {
        // Mybatis 配置文件
        String resource = "mybatis.cfg.xml";

        // 得到配置文件流
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 创建会话工厂，传入 MyBatis 的配置文件信息
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @org.junit.Test
    public void selectClassById() {
        // 通过工厂得到 SqlSession
        SqlSession session = sqlSessionFactory.openSession();

        ClassesMapper mapper = session.getMapper(ClassesMapper.class);
        try {
            Classes classes = mapper.selectClassById(1);
            session.commit();
            System.out.println(classes.getId() + "," + classes.getName() + ",["
                    + classes.getTeacher().getId() + ","
                    + classes.getTeacher().getName() + ","
                    + classes.getTeacher().getAge()+"]");

        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        }
        // 释放资源
        session.close();
    }
}