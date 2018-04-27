package yamanda.service;

import org.springframework.transaction.annotation.Transactional;

import yamanda.dao.VisitDao;
import yamanda.domain.Visit;
@Transactional
public class VisitService {
	private VisitDao visitDao;

	public VisitDao getVisitDao() {
		return visitDao;
	}

	public void setVisitDao(VisitDao visitDao) {
		this.visitDao = visitDao;
	}

	public void add(Visit visit) {
		visitDao.add(visit);
	}
	
}
