package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.JobWorkingCondition;

@Repository
public class JobWorkingConditionDao extends AbstractJpaDao<JobWorkingCondition> {
	
	public JobWorkingConditionDao() {
        setClazz(JobWorkingCondition.class);
    }
	
	public boolean isBkExist(String job) {
		if(findByBk(job).getId() == null) {
			return false;
		}else {
			return true;
		}
	}
	
	@SuppressWarnings("unchecked")
	public JobWorkingCondition findByBk(String job) {	
		List<JobWorkingCondition> list = super.entityManager
                .createQuery("FROM JobWorkingCondition WHERE job.id = :job")
                .setParameter("job", job)
                .getResultList();

		if (list.size() == 0) {
			return new JobWorkingCondition();
		}
		else {
			return list.get(0);
		}
	}

}
