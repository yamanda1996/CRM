package yamanda.dao;

import java.util.List;
import java.util.Set;

import yamanda.domain.LinkMan;

public interface LinkManDao {

	void add(LinkMan linkMan);

	List<LinkMan> findAll();

	LinkMan findByLinkid(int linkid);

	void update(LinkMan linkMan);

}
