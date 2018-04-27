package yamanda.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import yamanda.domain.Customer;

public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao{

	//添加客户
//	public void add(Customer customer) {
//		HibernateTemplate hibernateTemplate = this.getHibernateTemplate();  //得到模板
//		hibernateTemplate.save(customer);
//		
//	}
//
//	//查找所有客户
//	@SuppressWarnings("all")
//	public List<Customer> findAll() {
//		HibernateTemplate hibernateTemplate = this.getHibernateTemplate();
//		return (List<Customer>) hibernateTemplate.find("from Customer");
//		
//	}
//
//	//根据cid查找
//	public Customer findByCid(int cid) {
//		return (Customer) this.getHibernateTemplate().get(Customer.class, cid);
//		
//	}
//
//	//删除操作
//	public void delete(Customer custExist) {
//		this.getHibernateTemplate().delete(custExist);	
//	}
//
//	//修改操作
//	public void update(Customer customer) {
//		this.getHibernateTemplate().update(customer);
//	}

	//查找表中总的记录数
	public int findCount() {
		@SuppressWarnings("unchecked")
		List<Object> list = (List<Object>) this.getHibernateTemplate().find("select count(*) from Customer");  //查询得到的是一个list集合
		//从list中把值得到
		if(list!=null&&list.size()!=0){
			Object obj = list.get(0);
			Long lobj = (Long)obj;
			int count = lobj.intValue();
			return count;
		}
		return 0;
	}

	
	

	//查询每一页中记录的集合
	
	public List<Customer> findPage(int begin, int pageSize) {
		//使用hibernate底层代码实现    一般不这样用，都是在做复杂查询的时候再调用底层代码
		//得到sessionFactory
//		SessionFactory sessionFactory = this.getHibernateTemplate().getSessionFactory();
//		Session session = sessionFactory.getCurrentSession();  //得到与本地线程绑定的session
//		Query query = session.createQuery("from Customer");
//		//设置分页信息
//		query.setFirstResult(begin);
//		query.setMaxResults(pageSize);
//		query.list();
		
		
		
		
		//第二种方法：使用离线对象和hibernate模板中的方法
		/**
		 * 1.创建离线对象，设置对哪个实体类进行操作
		 * 2.调用hibernate模板中的方法实现
		 * 
		 */
		//创建离线对象，设置对哪个实体类进行操作
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		//第一个参数为离线对象，第二个是开始位置，第三个是每页记录数
		List<Customer> list = (List<Customer>) this.getHibernateTemplate().findByCriteria(criteria, begin, pageSize);
		
		return list;
	}

	
	public List<Customer> findCondition(Customer customer) {
		//第一种方式，底层代码实现
//		SessionFactory sessionFactory = this.getHibernateTemplate().getSessionFactory();
//		Session session = sessionFactory.getCurrentSession();  //得到与本地线程绑定的session
//		Query query = session.createQuery("from Customer where cust_name like ?");
//		query.setParameter( 0,"%"+customer.getCust_name()+"%");
//		List<Customer> list = query.list();
//		
		//第二种方法，调用模板中的find方法
//		List<Customer> list = (List<Customer>) this.getHibernateTemplate().
//				find("from Customer where cust_name like ?","%"+customer.getCust_name()+"%");
		//第三种方法，使用离线对象，再调用模板中的方法     非常适合于多条件查询
		//创建离线对象，设置对哪个实体类进行操作
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
//		criteria.setProjection(Projections.rowCount());  //得到表中一共有多少行记录
		//设置对实体类哪个属性进行约束
		criteria.add(Restrictions.like("cust_name","%"+customer.getCust_name()+"%" ));
		//调用hibernate模板中的方法得到list集合
		List<Customer> list = (List<Customer>) this.getHibernateTemplate().findByCriteria(criteria);
		return list;
	}




	//多条件组合查询
	public List<Customer> selectCustomer(Customer customer) {
//		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
//		criteria.add(Restrictions.like("cust_name","%"+customer.getCust_name()+"%" ));
//		criteria.add(Restrictions.like("cust_level","%"+customer.getCust_level()+"%" ));
//		criteria.add(Restrictions.like("cust_source","%"+customer.getCust_source()+"%" ));
//		return (List<Customer>) this.getHibernateTemplate().findByCriteria(criteria);
		
		//使用hibernate模板的find方法实现
		//拼接hql语句
//		List<Object> list = new ArrayList<Object>();  //利用list集合储存值
//		String hql = "from Customer where 1=1";
//		if(customer.getCust_name()!=null && !"".equals(customer.getCust_name())){
//			hql += " and cust_name=?";
//			list.add(customer.getCust_name());
//		}
//		if(customer.getCust_level()!=null && !"".equals(customer.getCust_level())){
//			hql += " and cust_level=?";
//			list.add(customer.getCust_level());
//		}
//		if(customer.getCust_source()!=null && !"".equals(customer.getCust_source())){
//			hql += " and cust_source=?";
//			list.add(customer.getCust_source());
//		}
//		/*System.out.println(hql);
//		System.out.println(list.toString());*/
//		return (List<Customer>) this.getHibernateTemplate().find(hql,list.toArray());
//		
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		if(customer.getCust_name()!=null && !"".equals(customer.getCust_name())){
			criteria.add(Restrictions.eq("cust_name", customer.getCust_name()));
		}
//		if(customer.getCust_level()!=null && !"".equals(customer.getCust_level())){
//			criteria.add(Restrictions.eq("cust_level", customer.getCust_level()));
//		}
		if(customer.getCust_source()!=null && !"".equals(customer.getCust_source())){
			criteria.add(Restrictions.eq("cust_source", customer.getCust_source()));
		}
		return (List<Customer>) this.getHibernateTemplate().findByCriteria(criteria);
	}




//	//利用json实现分页
//	public List<Customer> findPageJson(int begin, int rows, int page) {
//		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
//		this.getHibernateTemplate().findByCriteria(criteria, begin, rows);
//		return null;
//	}
//	
}
