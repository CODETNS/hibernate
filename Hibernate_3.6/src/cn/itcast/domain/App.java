package cn.itcast.domain;

import javax.security.auth.login.AppConfigurationEntry;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class App {
	private static SessionFactory sessionFactory;
	static{
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");//读取指定的主配置文件
		sessionFactory=cfg.buildSessionFactory();//根据配置文件生产Session工厂
	}
	@Test
	public void testSave()throws Exception{
		User user=new User();
		user.setName("张三");
		//保存
		
		Session session=sessionFactory.openSession();//打开一个session
		
		Transaction tx=session.beginTransaction();//开始事务
		
		session.save(user);
		
		tx.commit();//提交事务
		
		session.close();//关闭session,释放资源,不一定关闭连接
	}
	public void testGet()throws Exception{
		//获取
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		User user=(User) session.get(User.class, 1);
		System.out.println(user);
		tx.commit();
		session.close();
	}
	public static void main(String[] args) {
		App app=new App();
		try {
			app.testGet();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
