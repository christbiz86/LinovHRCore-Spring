package com.demo.dao;

import com.demo.model.Job;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class JobDao extends ParentDao {

	@SuppressWarnings("unchecked")
    @Transactional
    public List<Job> findAll(){
        return super.entityManager.createQuery("FROM Job").getResultList();
    }
    
    @Transactional
	public void save(Job job) {
		super.entityManager.merge(job);
	}
	
	@Transactional
	public void delete(String id) {
		Job job = findById(id);
		super.entityManager.remove(job);
	}
	
	public boolean isIdExist(String id) {
		if(findById(id).getId() == null) {
			return false;
		}else {
			return true;
		}
	}
	
	public boolean isBkExist(String code) {
		if(findByBk(code).getId() == null) {
			return false;
		}else {
			return true;
		}	 
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public Job findById(String id) {	
		List<Job> list = super.entityManager
                .createQuery("FROM Job WHERE id = :id")
                .setParameter("id", id)
                .getResultList();

		if (list.size() == 0) {
			return new Job();
		}
		else {
			return list.get(0);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public Job findByBk(String code) {	
		List<Job> list = super.entityManager
                .createQuery("FROM Job WHERE code = :code")
                .setParameter("code", code)
                .getResultList();

		if (list.size() == 0) {
			return new Job();
		}
		else {
			return list.get(0);
		}
	}

}
