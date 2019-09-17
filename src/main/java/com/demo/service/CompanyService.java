package com.demo.service;

import com.demo.dao.CompanyDao;
import com.demo.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CompanyService {

    @Autowired
    private CompanyDao companyDao;

    public List<Company> findAll(){
        return companyDao.findAll();
    }

    public List<Company> findById(UUID id){
        return companyDao.findById(id);
    }

}
