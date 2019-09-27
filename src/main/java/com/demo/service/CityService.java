package com.demo.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.CityDao;
import com.demo.model.City;

@Service
public class CityService {

    @Autowired
    private CityDao cityDao;

    public List<City> findAll(){
        return cityDao.findAll();
    }

    public City findById(String id){
        return cityDao.findOne(id);
    }
    
    public City findByBk(String code, String provinceId) {
    	return cityDao.findByBk(code, provinceId);
    }
    
    private void valIdExist(String id) throws Exception {
    	if(!cityDao.isIdExist(id)) {
    		throw new Exception("Data Is Not Found");
    	}
    }
    
    private void valIdNotNull(City city) throws Exception {
    	if(city.getId() == null || city.getId().isEmpty()) {
    		throw new Exception("Id Cannot be empty");
    	}
    }
    
    private void valNonBk(City city) throws Exception {
    	if(city.getCreatedAt() == null) {
    		throw new Exception("Time of Create Data cannot be empty");
    	}
    	if(city.getCreatedBy().isEmpty()) {
    		throw new Exception("Creator Data cannot be empty");
    	}
    	if(city.getName().isEmpty()) {
    		throw new Exception("City Name cannot be empty");
    	}
    	if(city.getVersion() == null) {
    		throw new Exception("Version cannot be empty");
    	}
    }
    
    private void valBkNotExist(City city) throws Exception {
    	if(cityDao.isBkExist(city.getCode(), city.getProvince().getId())) {
    		throw new Exception("Data already exist");
    	}
    }
    
    private void valBkNotChange(City city) throws Exception {
    	String province = findById(city.getId()).getProvince().getId();
    	String code = findById(city.getId()).getCode();
    	if(!city.getProvince().getId().equals(province)) {
    		throw new Exception("Province of City cannot be changed");
    	}
    	if(!city.getCode().equals(code)) {
    		throw new Exception("City Code cannot be changed");
    	}
    }
    
    private void valBkNotNull(City city) throws Exception {
    	if(city.getProvince() == null || city.getProvince().getId().isEmpty()) {
    		throw new Exception("Province Of City cannot be changed");
    	}
    	if(city.getCode().isEmpty()) {
    		throw new Exception("City Code cannot be changed");
    	}
    }
    
    private void valCreatedNotChange(City city) throws Exception {
    	City tempCity = findById(city.getId());
    	
    	if(city.getCreatedAt() != tempCity.getCreatedAt() && !city.getCreatedBy().equals(tempCity.getCreatedBy())) {
    		throw new Exception("Creator of City cannot be changed");
    	}
    }

    public void save(City city) throws Exception {
    	city.setCreatedAt(new Timestamp(System.currentTimeMillis()));
    	
    	valBkNotNull(city);
    	valNonBk(city);
    	valBkNotExist(city);
    	cityDao.create(city);
    }

    public void update(City city) throws Exception {
    	city.setCreatedAt(new Timestamp(System.currentTimeMillis()));
    	
    	valCreatedNotChange(city);
    	
    	valIdNotNull(city);
    	valIdExist(city.getId());
    	valBkNotNull(city);
    	valBkNotChange(city);
    	valBkNotNull(city);
    	cityDao.update(city);
    }

    public void delete(String id) throws Exception {
    	cityDao.deleteById(id);
    }
}
