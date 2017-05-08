# Hibernate
Hibernate基础

一、基础知识</br>
1.ORM:对象关系映射 Object Relational Mapping</br>
2.ORMAPI；</br>
SSH（Hibernate）</br>
3.Hibernate 与 JDBC的比较</br>

二、准备工作</br>
1.Hibernate tools (Jboss tools包含Hibernate tools)通过插件安装（对应eclipse版本安装）</br>

2.下载hibernate jar包+mysql驱动（当前我下载5.2版本）</br>

3.新建hibernate.cfg.xml</br>
1)配置数据库基本信息</br>
<!-- 配置链接数据库的基本信息 -->
		<property name="connection.username">root</property>
		<property name="connection.password">haibo1118</property>
		<property name="connection.driver_class">org.gjt.mm.mysql.Driver</property>
		<property name="connection.url">jdbc:mysql://127.0.0.1:3306/samp_db</property>
2)配置hibernate基本信息：
	数据库方言:查找jar包中,hibernate-release-5.2.10.Final/project/etc/hibernate.properties 找到对应数据库
	<!-- 配置hibernate数据库方言 -->
		<property name="dialect">org.hibernate.dialect.MySQLInnoDBDialect</property>
3)执行操作时是否在控制台打印sql:
	<!-- 执行操作时是否在控制台打印sql -->
		<property name="show_sql">true</property>
4)是否对sql格式化:分行显示
	<!-- 是否对sql格式化 -->
		<property name="format_sql">true</property>
5)指定自动生成数据表的策略:
	<!-- 指定自动生成数据表的策略 -->
		<property name="hbm2ddl.auto">update</property>		

4.创建持久化类，例如一个javaBean（本例News.java）

5.配置hibernate.hbm.xml对象-关系 映射文件，并将映射文件加到配置文件中
		<!-- 指定关联的.hbm.xml文件 -->
		<mapping resource="com/helloworld/News.hbm.xml"/>
6.编写访问数据库的代码

三、使用
		//1.创建sessionfatory对象
		//2.创建session对象
		//3.开启事务
		//4.执行保存操作
		//5.提交事务
		//6.关闭session
		//7.关闭sessionFatory对象
例如：
		//1.创建sessionfatory对象
		SessionFactory sessionFactory = null;
		//1)创建Configuration对象：对应hibernate的基本配置信息和对应关系映射信息
		Configuration configuration = new Configuration().configure();
		//4.0之前这么创建
		//sessionFactory = configuration.buildSessionFactory();
		//4.0之后添加对象serviceRegistry,hibernate任何配置和服务都需要在该对象中注册
		ServiceRegistry serviceRegistry = null;
		//版本4.0的serviceRegistryBuilder已经过时，采用5.0方式
		serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
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

四、遇到异常org.hibernate.MappingException: Unknown entity异常		

	


