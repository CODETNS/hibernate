package cn.itcast.dao;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.itcast.domain.User;

public class UserDao {
	/**
	 * ����
	 * @param user
	 */
	public void save(User user){
		Session session=HibernateUtils.openSession();//ͨ���������һ��session
		Transaction tx=null;
		try {
			tx=session.beginTransaction();//��ʼ����
			session.save(user);
			tx.commit();//�ύ����
		} catch (RuntimeException e) {
			tx.rollback();//�ع�����
			throw e;
		}finally{
			session.close();
		}
	}
	/**����
	 * @param user
	 */
	public void update(User user){
		Session session=HibernateUtils.openSession();//ͨ���������һ��session
		Transaction tx=null;
		try {
			tx=session.beginTransaction();//��ʼ����
			session.update(user);//����
			tx.commit();//�ύ����
		} catch (RuntimeException e) {
			tx.rollback();//�ع�����
			throw e;
		}finally{
			session.close();
		}
	}
	/**
	 * ɾ��
	 * @param id
	 */
	public void delete(int id){
		Session session=HibernateUtils.openSession();//ͨ���������һ��session
		Transaction tx=null;
		try {
			tx=session.beginTransaction();//��ʼ����
			Object user=session.get(User.class, id);//��ȡ����
			session.delete(user);//ɾ��������
			
			tx.commit();//�ύ����
		} catch (RuntimeException e) {
			tx.rollback();//�ع�����
			throw e;
		}finally{
			session.close();
		}
	}
	/**
	 * ��ѯ
	 * @param id
	 */
	public User getById(int id){
		Session session=HibernateUtils.openSession();//ͨ���������һ��session
		Transaction tx=null;
		try {
			tx=session.beginTransaction();//��ʼ����
			User user=(User)session.get(User.class, id);
			tx.commit();//�ύ����
			return user;
		} catch (RuntimeException e) {
			tx.rollback();//�ع�����
			throw e;
		}finally{
			session.close();
		}
	}
	/**
	 * ��ѯ����
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<User> findAll(){
		Session session=HibernateUtils.openSession();//ͨ���������һ��session
		Transaction tx=null;
		try {
			tx=session.beginTransaction();//��ʼ����
			List<User> listUser=session.createQuery("from User").list();//ʹ��hql��ѯ
			tx.commit();//�ύ����
			return listUser;
		} catch (RuntimeException e) {
			tx.rollback();//�ع�����
			throw e;
		}finally{
			session.close();
		}
	}
	/**
	 * ��ҳ��ѯ�û��б�
	 * @param firstResult ���ĸ�������ʼ��
	 * @param results �鼸����¼
	 * @return����ֵ������һҳ��¼+�ܼ�¼��
	 */
	@SuppressWarnings("unchecked")
	public QueryResult findAllUser(int firstResult,int results){
		Session session=HibernateUtils.openSession();//ͨ���������һ��session
		Transaction tx=null;
		try {
			tx=session.beginTransaction();//��ʼ����
			//��ѯ�����б�ʽһ
			/*Query query=session.createQuery("from User");
			query.setFirstResult(firstResult);
			query.setMaxResults(results);
			List<User> list=query.list();*/
			
			//��ѯ�����б�ʽ����������
			List<User> list=session.createQuery(//
					"FROM User")//
					.setFirstResult(firstResult)//
					.setMaxResults(results)//
					.list();
			//��ѯ�ܼ�¼��
			Long count=(Long)session.createQuery(//
					"SELECT COUNT(*) FROM User")//
					.uniqueResult();//uniqueResultΪΨһ�Ľ��,û���򷵻� null
			tx.commit();//�ύ����
			//���ؽ��
			return new QueryResult(list,count.intValue());
		} catch (RuntimeException e) {
			tx.rollback();//�ع�����
			throw e;
		}finally{
			session.close();
		}
	}

}
