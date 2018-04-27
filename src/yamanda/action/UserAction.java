package yamanda.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import yamanda.domain.User;
import yamanda.service.UserService;

public class UserAction extends ActionSupport {
	private UserService userService;
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	//属性封装获取表单提交的数据
	private String username;  //属性名字和表单输入项中的名字一致
	private String password;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	//登陆的方法
	public String login(){
		//封装到实体类对象
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		//调用userService中的方法实现
		User userExist = userService.login(user);
		if(userExist!=null){  //表示登陆成功
			//使用session保持登陆状态
			HttpServletRequest request = ServletActionContext.getRequest();  //得到request对象
			HttpSession session = request.getSession();
			session.setAttribute("userExist", userExist);  //把查出来的对象放到session中
			return "loginSuccess";
		}else{
			
			return "login";
		}
	}
	//查询所有用户
	public String findAll(){
		List<User> userList = userService.findAll();
		return "findAll";
	}
}
