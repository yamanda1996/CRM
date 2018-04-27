package yamanda.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import yamanda.domain.Customer;
import yamanda.domain.User;
import yamanda.domain.Visit;
import yamanda.service.CustomerService;
import yamanda.service.UserService;
import yamanda.service.VisitService;

public class VisitAction extends ActionSupport implements ModelDriven<Visit>{
	private VisitService visitService;
	private UserService userService;
	private CustomerService customerService;
	private Visit visit = new Visit();
	
	
	
	
	
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public VisitService getVisitService() {
		return visitService;
	}

	public void setVisitService(VisitService visitService) {
		this.visitService = visitService;
	}
	//到新增页面
	public String toAddPage(){
		//查询所有用户和所有客户
		//查询所有用户
		List<User> userList = userService.findAll();
		//查询所有客户
		List<Customer> customerList = customerService.findAll();
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("listUser", userList);
		request.setAttribute("listCustomer", customerList);
		
		return "toAddPage";
	}
	//添加拜访
	public String addVisit(){
		visitService.add(visit);
		return "addVisit";
	}

	//模型驱动封装
	public Visit getModel() {
		
		return visit;
	}
}
