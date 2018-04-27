package yamanda.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import yamanda.domain.Customer;
import yamanda.domain.PageBean;
import yamanda.service.CustomerService;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer>{
	private CustomerService customerService;
	//模型驱动封装，条件是属性名字和表单输入项名称一致
	private Customer customer = new Customer();
	public Customer getModel() {
		return customer;
	}
	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	//到添加页面，添加客户
	public String toAddPage(){
		return "toAddPage";
	}
	//添加客户
	public String add(){
		//模型驱动封装得到表单数据
		customerService.add(customer);
		
		return "add";
	}
	//定义list变量
	private List<Customer> list;
	//生成变量的get方法
	
	//客户列表
	public String list(){
		//List<Customer> list = customerService.findAll();
		//把list放到域对象中
//		HttpServletRequest request = ServletActionContext.getRequest();
//		request.setAttribute("list",list);
		//返回list到值栈中  使用struts标签和ognl表达式
		list = customerService.findAll();
		return "list";
	}
	
	public List<Customer> getList() {
		return list;
	}
	public void setList(List<Customer> list) {
		this.list = list;
	}
	//删除
	public String delete(){
		//使用模型驱动获取表单提交的cid值
		int cid = customer.getCid();
		//删除规范，先根据id查询，再调用方法删除
		Customer custExist = customerService.findByCid(cid);
		//调用方法删除
		if(custExist != null){  //如果不为空，才执行删除操作
			
			customerService.delete(custExist);
		}
		return "delete";
	}
	//修改操作  第一步，到显示页面中去
	public String showCustomer(){
		//使用模型驱动获取表单提交的cid值
		int cid = customer.getCid();
		//根据id查询，得到customer对象
		Customer custExist = customerService.findByCid(cid);
		//将其传到页面中去
		ServletActionContext.getRequest().setAttribute("customer", custExist);
		return "showCustomer";
	}
	//修改第二步，真正修改
	public String update(){
		//修改提交的表单中的数据已经封装到对象中了
		customerService.update(customer);
		return "update";
	}
	//分页方法
	public String listPage(){
		//使用属性封装的方法获取当前页
		//调用service中的方法完成pageBean的封装
		PageBean pageBean = customerService.listPage(currentPage);
		//将pageBean放到域对象中去
		ServletActionContext.getRequest().setAttribute("pageBean", pageBean);
		
		return "listPage";
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	private Integer currentPage;
	//条件查询
	public String listcondition(){
		//如果输入客户名称，按名称查询，如果不输入，查询所有
		if(customer.getCust_name()!=null&&!("".equals(customer.getCust_name()))){
			//不为空
			List<Customer> listCondition = customerService.findCondition(customer);
			/*ServletActionContext.getRequest().setAttribute("list", list);*/
			list = listCondition;
			
		}else{
			//为空，显示所有
			list = customerService.findAll();  //自动放到值栈中去
		}
		return "listcondition";
	}
	//到查询页面
	public String toSelectPage(){
		
		
		return "toSelectPage";
	}
	//多条件查询
	public String selectCustomer(){
		list = customerService.selectCustomer(customer);
		/*list = listCustomer; */   /*放到值栈中*/
//		ServletActionContext.getRequest().setAttribute("list", listCustomer);  //放到域对象中
		
		return "selectCustomer";
	}
	//返回customerJson
	public String customerJson() throws IOException{
		//查询所有客户，把list集合转换成json数据格式
		//json格式固定 {"total":数量，"rows":[{},{}]}
		//使用fastjson中的类中的方法实现转换,对象转换成对象格式，集合转换成数组格式
		//JSON.toJSONString(object);
		//对象转换
//		Customer customer = new Customer();
//		customer.setCust_name("能年铃奈");
//		customer.setCust_phone("110");
//		Customer cust = new Customer();
//		cust.setCust_name("广濑绫");
//		cust.setCust_phone("112");
//		//String json = JSON.toJSONString(customer);
//		//集合转换
//		List<Customer> list = new ArrayList<Customer>();
//		list.add(customer);
//		list.add(cust);
//		String json = JSON.toJSONString(list);
		
		//查询出所有客户，返回list集合
		List<Customer> list = customerService.findAll();
		//把list集合转换成json数据格式
		//结构是名称和值的结构，使用map集合进行数据的封装
		//创建map集合
		Map<String,Object> map = new HashMap<String, Object>();
		//添加数据
		map.put("total",list.size());
		map.put("rows", list);
		//把map转换成json数据格式
		String json = JSON.toJSONString(map);
		
		
		
		HttpServletResponse response = ServletActionContext.getResponse();
		//解决中文返回乱码问题
		//返回为json格式的时候，写application/json
		response.setContentType("application/json;charset=utf-8");
		//如果使用response返回数据，那么action的方法不能有返回值
		response.getWriter().write(json);
		return NONE;
	}
	//属性封装得到表单提交的page和rows属性
	private int page;  //当前页
	private int rows;  //每页行数
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	//返回分页json数据
	public String customerPageJson() throws IOException{
		//返回分页数据，total总记录数  rows分页数据json格式
		//传递参数：当前页、每页记录数、开始位置
		int begin = (page-1)*rows;  //开始位置
		//得到分页list集合
		List<Customer> customerList = customerService.findPageJson(begin,rows,page);
		//得到总记录数
		
		List<Customer> list = customerService.findAll();
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("total", list.size());
		map.put("rows", customerList);
		String json = JSON.toJSONString(map);
		
		HttpServletResponse response = ServletActionContext.getResponse();
		//解决中文返回乱码问题
		//返回为json格式的时候，写application/json
		response.setContentType("application/json;charset=utf-8");
		//如果使用response返回数据，那么action的方法不能有返回值
		response.getWriter().write(json);
		return NONE;
	}
	
}
