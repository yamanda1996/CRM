package yamanda.dao;

import java.util.List;
import java.util.Set;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import yamanda.domain.LinkMan;

public class LinkManDaoImpl extends HibernateDaoSupport implements LinkManDao{

	//增加联系人
	public void add(LinkMan linkMan) {
		this.getHibernateTemplate().save(linkMan);
		
	}

	//查询所有联系人
	public List<LinkMan> findAll() {
		
		return (List<LinkMan>) this.getHibernateTemplate().find("from LinkMan");
	}

	//根据id查询某个联系人
	public LinkMan findByLinkid(int linkid) {
		
		return  this.getHibernateTemplate().get(LinkMan.class, linkid);
	}

	//修改联系人信息
	public void update(LinkMan linkMan) {
		this.getHibernateTemplate().update(linkMan);
	}
	
}
