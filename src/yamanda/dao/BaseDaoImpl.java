package yamanda.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T>{
	
	private Class clazz;
	//添加
	public void add(T t) {
		this.getHibernateTemplate().save(t);
	}
	//构造方法   获取泛型类型的方法
	public BaseDaoImpl() {
		//得到当前运行类的class
		Class clazz = this.getClass();
		//得到当前运行类父类的参数化类型BaseDaoImpl<Customer>
		//使用type的子接口，parameterizedType
		ParameterizedType type = (ParameterizedType) clazz.getGenericSuperclass();
		
		//得到实际类型参数<Customer>
		Type[] types = type.getActualTypeArguments();
		//type接口的实现类就是class
		//将type转换成class对象
		Class pclass = (Class) types[0];
		this.clazz = pclass;    //将方法中的值赋给成员变量，这样在别的方法中就可以使用了
		
	}
	//修改
	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}

	//删除
	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
	}

	//按id查询
	public T findById(int id) {
		//return this.getHibernateTemplate().get(T, id);  不能这样写
		return (T) this.getHibernateTemplate().get(clazz, id);
	}

	//查询所有
	public List<T> findAll() {
		//return (List<T>) this.getHibernateTemplate().find("from T");  //不能这样写
		return (List<T>) this.getHibernateTemplate().find("from "+clazz.getSimpleName());  //使用getSimpleName获得类的名称
	}

	

}
