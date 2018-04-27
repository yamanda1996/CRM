package yamanda.dao;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;

import yamanda.domain.User;

public class UserDaoImpl implements UserDao{
	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	//登录方法
	@SuppressWarnings("all")
	public User login(User user) {
		//登录的查询操作
		//根据用户名和密码查询
		List<User> list = (List<User>) hibernateTemplate.find("from User where username=? and password=?", user.getUsername(),user.getPassword());
		//如果查询之后没有结果，list里面没有值，这取0则会出现下标越界异常
		if(list.size()!=0 && list!=null){  //集合不为空并且长度不为0
			
			return list.get(0);
		}else{
			return null;
		}
	}

	//查询所有用户
	public List<User> findAll() {
		List<User> list =  (List<User>) this.getHibernateTemplate().find("from User");
		return list;
	}
	
}
