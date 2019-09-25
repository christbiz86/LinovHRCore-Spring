package com.demo.dao;

import com.demo.model.Company;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CompanyDao extends AbstractJpaDao<Company> {
	public CompanyDao() {
		setClazz(Company.class);
	}
	
	@SuppressWarnings("unchecked")
	public Company findByBk(String code, String tenantId) {
		List<Company> company = super.entityManager.createQuery("FROM Company WHERE code=:code AND tenant.id=:tenantId")
				.setParameter("code", code)
				.setParameter("tenantId", tenantId).getResultList();
		
		if(company.size() == 0) {
			return new Company();
		} else {
			return company.get(0);
		}
	}
	
	public boolean isBkExist(String code, String tenantId) {
		if(findByBk(code, tenantId).getId() == null) {
			return false;
		} else {
			return true;
		}
	}
}