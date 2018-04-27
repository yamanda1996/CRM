package yamanda.service;

import java.util.List;
import java.util.Set;

import org.springframework.transaction.annotation.Transactional;

import yamanda.dao.LinkManDao;
import yamanda.domain.LinkMan;
@Transactional
public class LinkManService {
	private LinkManDao linkManDao;

	public LinkManDao getLinkManDao() {
		return linkManDao;
	}

	public void setLinkManDao(LinkManDao linkManDao) {
		this.linkManDao = linkManDao;
	}

	public void add(LinkMan linkMan) {
		linkManDao.add(linkMan);
	}

	public List<LinkMan> findAll() {
		
		return linkManDao.findAll();
	}

	public LinkMan findByLinkid(int linkid) {
		
		return linkManDao.findByLinkid(linkid);
	}

	public void update(LinkMan linkMan) {
		linkManDao.update(linkMan);
		
	}
	
}
