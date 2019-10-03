package com.demo.service;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.CompanyDao;
import com.demo.model.Company;

@Service
public class CompanyService {

    @Autowired
    private CompanyDao companyDao;
    
    public List<Company> findAll(){
        return companyDao.findAll();
    }

    public Company findById(String id){
        return companyDao.findOne(id);
    }

    public Company findByBk(String code, String tenantId) {
    	return companyDao.findByBk(code, tenantId);
    }
    
    private void valIdExist(String id) throws Exception {
    	if(!companyDao.isIdExist(id)) {
    		throw new Exception("Data is Not Found");
    	}
    }
    
    private void valIdNotNull(Company company) throws Exception {
    	if(company.getId() == null || company.getId().isEmpty()) {
    		throw new Exception("Id Cannot be empty");
    	}
    }
    
    private void valNonBk(Company company) throws Exception {
    	if(company.getName().trim().isEmpty()) {
    		throw new Exception("Company Name cannot be empty");
    	}
    	if(company.getCompanyTaxNumber().trim().isEmpty()) {
    		throw new Exception("Company Tax Number cannot be empty");
    	}
    	if(company.getSortOrder() == null) {
    		throw new Exception("Sort Order Company cannot be empty");
    	}    		
    }
    
    private void valBkNotExist(Company company) throws Exception {
    	if(companyDao.isBkExist(company.getCode(), company.getTenant().getId())) {
    		throw new Exception("Data already exist");
    	}
    }
    
    private void valBkNotChange(Company company) throws Exception {
    	String tenant = findById(company.getId()).getTenant().getId();
    	String code = findById(company.getId()).getCode();
    	
    	if(!company.getTenant().getId().equals(tenant)) {
    		throw new Exception("Tenant Company cannot be changed");
    	}
    	if(!company.getCode().equals(code)) {
    		throw new Exception("Company Code cannot be changed");
    	}
    }
    
    private void valBkNotNull(Company company) throws Exception {
    	if(company.getTenant() == null || company.getTenant().getId().isEmpty()) {
    		throw new Exception("Tenant Company cannot be empty");
    	}
    	if(company.getCode().isEmpty()) {
    		throw new Exception("Company Code cannot be empty");
    	}
    }

    public void save(Company company) throws Exception {
    	company.setCreatedAt(new Timestamp(System.currentTimeMillis()));
    	
    	valBkNotNull(company);
    	valNonBk(company);
    	valBkNotExist(company);
    	companyDao.create(company);
    }

    public void update(Company company) throws Exception {
    	company.setCreatedAt(new Timestamp(System.currentTimeMillis()));
    	
    	valIdNotNull(company);
    	valIdExist(company.getId());
    	valBkNotNull(company);
    	valBkNotChange(company);
    	valNonBk(company);
    	companyDao.update(company);
    }

    public void delete(String id) throws Exception {
    	companyDao.deleteById(id);
    }
}
