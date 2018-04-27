package yamanda.domain;

import java.util.HashSet;
import java.util.Set;

public class User {
	private Integer uid;
	private String username;
	private String password;
	private String address;
	//用户的所有拜访记录
	private Set<Visit> userVisitSet = new HashSet<Visit>();
	
	
	public Set<Visit> getUserVisitSet() {
		return userVisitSet;
	}
	public void setUserVisitSet(Set<Visit> userVisitSet) {
		this.userVisitSet = userVisitSet;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public User(Integer uid, String username, String password, String address) {
		super();
		this.uid = uid;
		this.username = username;
		this.password = password;
		this.address = address;
	}
	public User() {
		super();
	}
	@Override
	public String toString() {
		return "User [uid=" + uid + ", username=" + username + ", password=" + password + ", address=" + address + "]";
	}
	
}
