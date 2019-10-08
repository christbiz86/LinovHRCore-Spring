package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.EmployeeStatus;

@Repository
public class EmployeeStatusDao extends AbstractJpaDao<EmployeeStatus>{

	public EmployeeStatusDao() {
		setClazz(EmployeeStatus.class);
	}

	@SuppressWarnings("unchecked")
    public List<EmployeeStatus> findAll(Integer offset, Integer limit){
        return super.entityManager.createQuery("FROM EmployeeStatus")
                .setFirstResult(offset).setMaxResults(limit).getResultList();
    }

	@SuppressWarnings("unchecked")
	public EmployeeStatus findByBk(String companyId,String code) {
		List<EmployeeStatus> list= super.entityManager.createQuery("FROM EmployeeStatus WHERE company.id=:companyId AND code=:code ")
				.setParameter("companyId", companyId)
				.setParameter("code", code)
				.getResultList();
		
		if (list.size() == 0) {
			return new EmployeeStatus();
		}
		else {
			return (EmployeeStatus)list.get(0);
		}
	}
	
	public boolean isBkExist(EmployeeStatus employeeStatus) {
		if(findByBk(employeeStatus.getCompany().getId(),employeeStatus.getCode()).getId() == null) {
			return false;
		} else {
			return true;
		}
	}
}
