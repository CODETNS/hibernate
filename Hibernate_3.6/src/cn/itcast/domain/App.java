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
		cfg.configure("hibernate.cfg.xml");//��ȡָ�����������ļ�
		sessionFactory=cfg.buildSessionFactory();//���������ļ�����Session����
	}
	@Test
	public void testSave()throws Exception{
		User user=new User();
		user.setName("����");
		//����
		
		Session session=sessionFactory.openSession();//��һ��session
		
		Transaction tx=session.beginTransaction();//��ʼ����
		
		session.save(user);
		
		tx.commit();//�ύ����
		
		session.close();//�ر�session,�ͷ���Դ,��һ���ر�����
	}
	public void testGet()throws Exception{
		//��ȡ
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
