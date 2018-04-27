package yamanda.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import yamanda.dao.UserDao;
import yamanda.domain.User;

@Transactional
public class UserService {
	private UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public User login(User user) {
		//调用Dao中的方法
		
		return userDao.login(user);
	}

	public List<User> findAll() {
		return userDao.findAll();
	}
	
}
