package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.model.JobResponsibility;

@Repository
public class JobResponsibilityDao extends ParentDao {
	
	@SuppressWarnings("unchecked")
    @Transactional
    public List<JobResponsibility> findAll(){
        return super.entityManager.createQuery("FROM JobResponsibility").getResultList();
    }
    
    @Transactional
	public void save(JobResponsibility jobResponsibility) {
		super.entityManager.merge(jobResponsibility);
	}
	
	@Transactional
	public void delete(String id) {
		JobResponsibility jobResponsibility = findById(id);
		super.entityManager.remove(jobResponsibility);
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
	public JobResponsibility findById(String id) {	
		List<JobResponsibility> list = super.entityManager
                .createQuery("FROM JobResponsibility WHERE id = :id")
                .setParameter("id", id)
                .getResultList();

		if (list.size() == 0) {
			return new JobResponsibility();
		}
		else {
			return list.get(0);
		}
	}

}
