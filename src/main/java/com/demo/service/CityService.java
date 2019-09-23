package com.demo.service;

import com.demo.dao.CityDao;
import com.demo.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    @Autowired
    private CityDao cityDao;

    public List<City> findAll(Integer offset, Integer limit){
        return cityDao.findAll(offset,limit);
    }

    public List<City> findById(String id){
        return cityDao.findById(id);
    }

}
