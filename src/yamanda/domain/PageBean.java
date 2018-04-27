package yamanda.domain;

import java.util.List;

public class PageBean {
	private Integer currentPage; //当前页
	private Integer totalCount;  //总记录数
	private Integer pageSize;  //每页记录数
	private Integer totalPage;  //总页数
	private Integer begin;  //开始位置
	private List<Customer> list;  //每页显示的list集合
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Integer getBegin() {
		return begin;
	}
	public void setBegin(Integer begin) {
		this.begin = begin;
	}
	public List<Customer> getList() {
		return list;
	}
	public void setList(List<Customer> list) {
		this.list = list;
	}
	public PageBean(Integer currentPage, Integer totalCount, Integer pageSize, Integer totalPage, Integer begin,
			List<Customer> list) {
		super();
		this.currentPage = currentPage;
		this.totalCount = totalCount;
		this.pageSize = pageSize;
		this.totalPage = totalPage;
		this.begin = begin;
		this.list = list;
	}
	public PageBean() {
		super();
	}
	@Override
	public String toString() {
		return "PageBean [currentPage=" + currentPage + ", totalCount=" + totalCount + ", pageSize=" + pageSize
				+ ", totalPage=" + totalPage + ", begin=" + begin + ", list=" + list + "]";
	}
	
	
}
