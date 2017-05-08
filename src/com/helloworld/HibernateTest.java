package com.helloworld;

import static org.junit.Assert.*;

import java.sql.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.Test;

public class HibernateTest {

	@Test
	public void test() {
		//1.创建sessionfatory对象
		SessionFactory sessionFactory = null;
		//1)创建Configuration对象：对应hibernate的基本配置信息和对应关系映射信息
		Configuration configuration = new Configuration().configure();
		//4.0之前这么创建
		//sessionFactory = configuration.buildSessionFactory();
		//4.0之后添加对象serviceRegistry,hibernate任何配置和服务都需要在该对象中注册
		//版本4.0的serviceRegistryBuilder已经过时，采用5.0方式
		ServiceRegistry serviceRegistry = serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		//2.创建session对象
		Session session = sessionFactory.openSession();
		//3.开启事务
		Transaction transaction = session.beginTransaction();
		//4.执行保存操作
		News news = new News("Java", "xionghaibo", new Date(new java.util.Date().getTime()));
		session.save(news);
		//5.提交事务
		transaction.commit();
		//6.关闭session
		session.close();
		//7.关闭sessionFatory对象
		sessionFactory.close();
	}

}
