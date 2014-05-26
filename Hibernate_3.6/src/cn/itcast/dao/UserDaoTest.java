package cn.itcast.dao;

import java.util.List;

import org.junit.Test;

import cn.itcast.domain.User;

public class UserDaoTest {
	private UserDao userDao=new UserDao();

	@Test
	public void testSave() {
		User user=new User();
		user.setName("张三");
		
		userDao.save(user);
		
	}

	@Test
	public void testUpdate() {
		User user=userDao.getById(2);
		user.setName("李四");
		userDao.update(user);
	}

	@Test
	public void testDelete() {
		userDao.delete(2);
	}

	@Test
	public void testGetById() {
		User user=userDao.getById(2);
		System.out.println(user);
	}

	@Test
	public void testFindAll() {
		List<User> list=userDao.findAll();
		for(User user:list){
			System.out.println(user);
		}
	}
//	-------------------------------------------
	@Test
	public void testSave_25() {
		for(int i=0;i<25;i++){
			User user=new User();
			user.setName("Test"+i);
			userDao.save(user);//保存
		}
	}
	
	
	@Test
	public void testFindAllUser() {
//		QueryResult qr=userDao.findAllUser(0, 10);
//		QueryResult qr=userDao.findAllUser(10, 10);
		QueryResult qr=userDao.findAllUser(20, 10);
		System.out.println("总记录数："+qr.getResult());
		for(User user:(List<User>)qr.getList()){
			System.out.println(user);
		}
	}

}
