
package com.demo.dao;

import java.util.List;

import javax.persistence.PostPersist;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.model.Job;

@Repository
public class JobDao extends AbstractJpaDao<Job> {

    public JobDao() {
        setClazz(Job.class);
    }
    
    @PostPersist
    private void test(Job job) {
    	System.out.println("berhasil save : "+job.getName());
    }

    @SuppressWarnings("unchecked")
    @Transactional
	public Job findByCode(String code) {
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

    @SuppressWarnings("unchecked")
    @Transactional
	public Job findByBk(String code, String company) {
		List<Job> list = super.entityManager
                .createQuery("FROM Job WHERE code = :code AND company.id = :company")
                .setParameter("code", code)
                .setParameter("company", company)
                .getResultList();

        if (list.size() == 0) {
            return new Job();
        }
        else {
            return list.get(0);
        }
    }

    public boolean isCodeExist(String code) {
        if(findByCode(code).getId() == null) {
            return false;
        }else {
            return true;
        }
    }

    public boolean isBkExist(String code, String company) {
        if(findByBk(code, company).getId() == null) {
            return false;
        }else {
            return true;
        }
    }
}

