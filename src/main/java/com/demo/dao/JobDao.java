package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.Job;

@Repository
public class JobDao extends AbstractJpaDao<Job> {

    public JobDao() {
        setClazz(Job.class);
    }
<<<<<<< HEAD

    @SuppressWarnings("unchecked")
    @Transactional
    public Job findByCode(String code) {
        List<Job> list = super.entityManager
=======
	
	@SuppressWarnings("unchecked")
	public Job findByCode(String code) {
		List<Job> list = super.entityManager
>>>>>>> abb6ec74144fab46ebfffb8c147424e7fe2ffe4a
                .createQuery("FROM Job WHERE code = :code")
                .setParameter("code", code)
                .getResultList();

<<<<<<< HEAD
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
=======
		if (list.size() == 0) {
			return new Job();
		}
		else {
			return list.get(0);
		}
	}
	
	@SuppressWarnings("unchecked")
	public Job findByBk(String code, String company) {
		List<Job> list = super.entityManager
>>>>>>> abb6ec74144fab46ebfffb8c147424e7fe2ffe4a
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
