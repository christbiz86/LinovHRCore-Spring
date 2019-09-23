package com.demo.service;

import com.demo.dao.CountryDao;
import com.demo.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    @Autowired
    private CountryDao countryDao;

    public List<Country> findAll(){
        return countryDao.findAll();
    }

    public List<Country> findById(String id){
        return countryDao.findById(id);
    }

    public List<Country> findByCode(String code){
        return countryDao.findByCode(code);
    }

}
