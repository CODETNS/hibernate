package cn.itcast.dao;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.itcast.domain.User;

public class UserDao {
	/**
	 * 保存
	 * @param user
	 */
	public void save(User user){
		Session session=HibernateUtils.openSession();//通过工具类打开一个session
		Transaction tx=null;
		try {
			tx=session.beginTransaction();//开始事务
			session.save(user);
			tx.commit();//提交事务
		} catch (RuntimeException e) {
			tx.rollback();//回滚事务
			throw e;
		}finally{
			session.close();
		}
	}
	/**更新
	 * @param user
	 */
	public void update(User user){
		Session session=HibernateUtils.openSession();//通过工具类打开一个session
		Transaction tx=null;
		try {
			tx=session.beginTransaction();//开始事务
			session.update(user);//操作
			tx.commit();//提交事务
		} catch (RuntimeException e) {
			tx.rollback();//回滚事务
			throw e;
		}finally{
			session.close();
		}
	}
	/**
	 * 删除
	 * @param id
	 */
	public void delete(int id){
		Session session=HibernateUtils.openSession();//通过工具类打开一个session
		Transaction tx=null;
		try {
			tx=session.beginTransaction();//开始事务
			Object user=session.get(User.class, id);//获取对象
			session.delete(user);//删除表数据
			
			tx.commit();//提交事务
		} catch (RuntimeException e) {
			tx.rollback();//回滚事务
			throw e;
		}finally{
			session.close();
		}
	}
	/**
	 * 查询
	 * @param id
	 */
	public User getById(int id){
		Session session=HibernateUtils.openSession();//通过工具类打开一个session
		Transaction tx=null;
		try {
			tx=session.beginTransaction();//开始事务
			User user=(User)session.get(User.class, id);
			tx.commit();//提交事务
			return user;
		} catch (RuntimeException e) {
			tx.rollback();//回滚事务
			throw e;
		}finally{
			session.close();
		}
	}
	/**
	 * 查询所有
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<User> findAll(){
		Session session=HibernateUtils.openSession();//通过工具类打开一个session
		Transaction tx=null;
		try {
			tx=session.beginTransaction();//开始事务
			List<User> listUser=session.createQuery("from User").list();//使用hql查询
			tx.commit();//提交事务
			return listUser;
		} catch (RuntimeException e) {
			tx.rollback();//回滚事务
			throw e;
		}finally{
			session.close();
		}
	}
	/**
	 * 分页查询用户列表
	 * @param firstResult 从哪个索引开始查
	 * @param results 查几条记录
	 * @return返回值包括第一页记录+总记录数
	 */
	@SuppressWarnings("unchecked")
	public QueryResult findAllUser(int firstResult,int results){
		Session session=HibernateUtils.openSession();//通过工具类打开一个session
		Transaction tx=null;
		try {
			tx=session.beginTransaction();//开始事务
			//查询数据列表方式一
			/*Query query=session.createQuery("from User");
			query.setFirstResult(firstResult);
			query.setMaxResults(results);
			List<User> list=query.list();*/
			
			//查询数据列表方式二，方法链
			List<User> list=session.createQuery(//
					"FROM User")//
					.setFirstResult(firstResult)//
					.setMaxResults(results)//
					.list();
			//查询总记录数
			Long count=(Long)session.createQuery(//
					"SELECT COUNT(*) FROM User")//
					.uniqueResult();//uniqueResult为唯一的结果,没有则返回 null
			tx.commit();//提交事务
			//返回结果
			return new QueryResult(list,count.intValue());
		} catch (RuntimeException e) {
			tx.rollback();//回滚事务
			throw e;
		}finally{
			session.close();
		}
	}

}
