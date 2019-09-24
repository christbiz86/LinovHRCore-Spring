package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.model.JobGrade;

@Repository
public class JobGradeDao extends AbstractJpaDao<JobGrade> {
	
	public JobGradeDao() {
        setClazz(JobGrade.class);
    }
	
	public boolean isBkExist(String job, String grade) {
		if(findByBk(job, grade).getId() == null) {
			return false;
		}else {
			return true;
		}	 
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public JobGrade findByBk(String job, String grade) {	
		List<JobGrade> list = super.entityManager
                .createQuery("FROM JobGrade WHERE job.id = :job AND grade.id = :grade")
                .setParameter("job", job)
                .setParameter("grade", grade)
                .getResultList();

		if (list.size() == 0) {
			return new JobGrade();
		}
		else {
			return list.get(0);
		}
	}

}
