package yamanda.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import yamanda.dao.CustomerDao;
import yamanda.domain.Customer;
import yamanda.domain.PageBean;
@Transactional
public class CustomerService {
	private CustomerDao customerDao;

	public CustomerDao getCustomerDao() {
		return customerDao;
	}

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	public void add(Customer customer) {
		customerDao.add(customer);
	}

	public List<Customer> findAll() {
		return customerDao.findAll();
		
	}

	public Customer findByCid(int cid) {
		
		return customerDao.findById(cid);
	}

	public void delete(Customer custExist) {
		customerDao.delete(custExist);
	}

	public void update(Customer customer) {
		customerDao.update(customer);
	}
	//封装分页的数据到pageBean的对象中
	public PageBean listPage(Integer currentPage) {
		//创建pageBean对象
		PageBean pageBean = new PageBean();
		pageBean.setCurrentPage(currentPage);
		int totalCount = customerDao.findCount();
		pageBean.setTotalCount(totalCount);
		int pageSize = 3;
		pageBean.setPageSize(pageSize);
		int totalPage = 1;
		//计算总页数
		if(totalCount%pageSize == 0){  
			totalPage = totalCount/pageSize;  //如果能整除，则为商
		}else{
			totalPage = totalCount/pageSize + 1;  //如果不能整除，则为商加一
		}
		pageBean.setTotalPage(totalPage);
		int begin = (currentPage-1)*pageSize;  //计算当前页开始位置
		pageBean.setBegin(begin);
		List<Customer> list = customerDao.findPage(begin,pageSize);  //查询每页的记录集合
		pageBean.setList(list);
		
		return pageBean;
	}

	public List<Customer> findCondition(Customer customer) {
		
		return customerDao.findCondition(customer);
	}

	public List<Customer> selectCustomer(Customer customer) {
		
		return customerDao.selectCustomer(customer);
	}

//	public List<Customer> findPageJson(int begin, int rows, int page) {
//		return customerDao.findPageJson(begin,rows,page);
//	}
	public List<Customer> findPageJson(int begin,int rows,int page){
		return customerDao.findPage(begin, rows);
	}

	
	
}
