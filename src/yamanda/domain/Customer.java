package yamanda.domain;

import java.util.HashSet;
import java.util.Set;

public class Customer {
	private Integer cid;
	private String cust_name;
	private String cust_level;
	private String cust_source;
	private String cust_phone;
	private String cust_mobile;
	private Dictionary dictionary;
	
	
	
	public Dictionary getDictionary() {
		return dictionary;
	}
	public void setDictionary(Dictionary dictionary) {
		this.dictionary = dictionary;
	}
	//表示该客户的所有拜访记录
	private Set<Visit> customerVisitSet = new HashSet<Visit>();
	
	public Set<Visit> getCustomerVisitSet() {
		return customerVisitSet;
	}
	public void setCustomerVisitSet(Set<Visit> customerVisitSet) {
		this.customerVisitSet = customerVisitSet;
	}
	private Set<LinkMan> setLinkMan = new HashSet<LinkMan>();
	
	public Set<LinkMan> getSetLinkMan() {
		return setLinkMan;
	}
	public void setSetLinkMan(Set<LinkMan> setLinkMan) {
		this.setLinkMan = setLinkMan;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	public String getCust_level() {
		return cust_level;
	}
	public void setCust_level(String cust_level) {
		this.cust_level = cust_level;
	}
	public String getCust_source() {
		return cust_source;
	}
	public void setCust_source(String cust_source) {
		this.cust_source = cust_source;
	}
	public String getCust_phone() {
		return cust_phone;
	}
	public void setCust_phone(String cust_phone) {
		this.cust_phone = cust_phone;
	}
	public String getCust_mobile() {
		return cust_mobile;
	}
	public void setCust_mobile(String cust_mobile) {
		this.cust_mobile = cust_mobile;
	}
	public Customer(Integer cid, String cust_name, String cust_level, String cust_source, String cust_phone,
			String cust_mobile) {
		super();
		this.cid = cid;
		this.cust_name = cust_name;
		this.cust_level = cust_level;
		this.cust_source = cust_source;
		this.cust_phone = cust_phone;
		this.cust_mobile = cust_mobile;
	}
	public Customer() {
		super();
	}
	@Override
	public String toString() {
		return "Customer [cid=" + cid + ", cust_name=" + cust_name + ", cust_level=" + cust_level + ", cust_source="
				+ cust_source + ", cust_phone=" + cust_phone + ", cust_mobile=" + cust_mobile + "]";
	}
	
}
