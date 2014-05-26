package cn.itcast.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
	//SessionFactoryȫ��ֻ��Ҫ��һ���Ϳ�����
	private static SessionFactory sessionFactory;
	static{
	/*	Configuration cfg=new Configuration();
		cfg.configure();//��ȡĬ�ϵ������ļ�
//		cfg.configure("hibernate.cfg.xml");//��ȡָ��λ�õ������ļ�
		sessionFactory=cfg.buildSessionFactory();*/
		sessionFactory=new Configuration()//
				.configure()//
				.buildSessionFactory();
	}
	/**
	 * ��ȡȫ��Ψһ��SessionFactory
	 * @return
	 */
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	/**
	 * ��ȫ��Ψһ��SessionFactory�д�һ��session
	 * @return
	 */
	public static Session openSession(){
		return sessionFactory.openSession();
	}
}
