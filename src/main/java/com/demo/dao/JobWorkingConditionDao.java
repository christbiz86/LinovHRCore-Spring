package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.model.JobWorkingCondition;

@Repository
public class JobWorkingConditionDao extends ParentDao {
	
	@SuppressWarnings("unchecked")
    @Transactional
    public List<JobWorkingCondition> findAll(){
        return super.entityManager.createQuery("FROM JobWorkingCondition").getResultList();
    }
    
    @Transactional
	public void save(JobWorkingCondition jobWorkingCond) {
		super.entityManager.merge(jobWorkingCond);
	}
	
	@Transactional
	public void delete(String id) {
		JobWorkingCondition jobWorkingCond = findById(id);
		super.entityManager.remove(jobWorkingCond);
	}
	
	public boolean isIdExist(String id) {
		if(findById(id).getId() == null) {
			return false;
		}else {
			return true;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public JobWorkingCondition findById(String id) {	
		List<JobWorkingCondition> list = super.entityManager
                .createQuery("FROM JobWorkingCondition WHERE id = :id")
                .setParameter("id", id)
                .getResultList();

		if (list.size() == 0) {
			return new JobWorkingCondition();
		}
		else {
			return list.get(0);
		}
	}

}
