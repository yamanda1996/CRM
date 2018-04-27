package yamanda.dao;

import java.util.List;

import yamanda.domain.Customer;

public interface CustomerDao extends BaseDao<Customer>{

//	void add(Customer customer);

//	List<Customer> findAll();

//	Customer findByCid(int cid);

//	void delete(Customer custExist);

//	void update(Customer customer);

	int findCount();

	List<Customer> findPage(int begin, int pageSize);

	List<Customer> findCondition(Customer customer);

	List<Customer> selectCustomer(Customer customer);

//	List<Customer> findPageJson(int begin, int rows, int page);

}
