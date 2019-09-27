package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.JobResponsibility;

@Repository
public class JobResponsibilityDao extends AbstractJpaDao<JobResponsibility> {
	
	public JobResponsibilityDao() {
		setClazz(JobResponsibility.class);
	}
	
	public boolean isBkExist(String id) {
		if(findByBk(id).getId() == null) {
			return false;
		}else {
			return true;
		}
	}
	
	@SuppressWarnings("unchecked")
	public JobResponsibility findByBk(String job) {
		List<JobResponsibility> list = super.entityManager
                .createQuery("FROM JobResponsibility WHERE job.id = :job")
                .setParameter("job", job)
                .getResultList();

		if (list.size() == 0) {
			return new JobResponsibility();
		}
		else {
			return list.get(0);
		}
	}

}
