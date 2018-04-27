package yamanda.action;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import yamanda.domain.Customer;
import yamanda.domain.LinkMan;
import yamanda.service.CustomerService;
import yamanda.service.LinkManService;

public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan>{
	private LinkMan linkMan = new LinkMan();
	private LinkManService linkManService;
	//注入客户的service对象
	private CustomerService customerService;
	/**
	 * 文件上传：
	 * 需要上传的文件流
	 * 需要上传的文件名称
	 * 1.在action中成员变量的位置定义变量（命名规范），一个表示上传的文件，一个表示文件名称
	 * 2.生成变量的get和set方法
	 * 还有一个变量，上传文件的mime类型（不需要自己获取，本身会自动获取）
	 * 
	 */
	//上传文件   命名规范：变量名称需要是表单里面文件上传项的name值
	private File upload;
	//上传文件名称   命名规范：变量名称需要是表单里面文件上传项的name值+FileName
	private String uploadFileName;
	
	
	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public LinkManService getLinkManService() {
		return linkManService;
	}

	public void setLinkManService(LinkManService linkManService) {
		this.linkManService = linkManService;
	}
	//到新增页面
	public String toAddPage(){
		//查询所有客户，把所有客户的list集合传到页面中去进行显示，使用域对象即可
		//调用客户service中的方法得到所有客户
		List<Customer> listCustomer = customerService.findAll();
		ServletActionContext.getRequest().setAttribute("listCustomer", listCustomer);  //将查询出来的客户列表放到域对象中
		
		return "toAddPage";
	}
	//新增联系人
	public String add() throws IOException{
		/**
		 * cid是客户的id值，无法自动封装，
		 * 把cid封装到linkman实体类中的customer对象中
		 */
		//用原始方式
//		String scid = (String) ServletActionContext.getRequest().getAttribute("cid");
//		int cid = Integer.parseInt(scid);
		//判断是否需要上传文件
		if(upload!=null){
			//写上传代码
			//在服务器文件夹中创建文件
			File serverFile = new File("E:\\file"+"/"+uploadFileName);
			
			//把上传的文件复制到服务器文件里面
			FileUtils.copyFile(upload, serverFile);  //把本地文件复制到服务器文件中
			
			
		}
		linkManService.add(linkMan);
		return "add";
	}
	//查询所有联系人
	public String list(){
		List<LinkMan> list = linkManService.findAll();
		ServletActionContext.getRequest().setAttribute("list", list);
		
		return "list";
	}
	//显示某个联系人信息
	public String showLinkMan(){
		//使用模型驱动得到id值
		int linkid = linkMan.getLinkid();
		//根据id查询联系人对象
		LinkMan linkMan = linkManService.findByLinkid(linkid);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("linkman", linkMan);
		
		//还需要所有客户的list集合
		List<Customer> listCustomer = customerService.findAll();
		request.setAttribute("listCustomer", listCustomer);
		
		
		return "showLinkMan";
	}
	
	
	

	//模型驱动封装
	public LinkMan getModel() {
		
		return linkMan;
	}
	//修改联系人
	public String update(){
		linkManService.update(linkMan);
		
		return "update";
	}
	
}
