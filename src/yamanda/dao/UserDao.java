package yamanda.dao;

import java.util.List;

import yamanda.domain.User;

public interface UserDao {

	User login(User user);

	List<User> findAll();

}
