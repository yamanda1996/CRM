package yamanda.dao;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import yamanda.domain.Visit;

public class VisitDaoImpl extends HibernateDaoSupport implements VisitDao{

	//项数据库中添加拜访
	public void add(Visit visit) {
		this.getHibernateTemplate().save(visit);
		
	}

}
