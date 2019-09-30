package com.demo.service;

import com.demo.dao.ProvinceDao;
import com.demo.model.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinceService {

    @Autowired
    private ProvinceDao provinceDao;
    
    public void valIdExist(String id) throws Exception {
		if(!provinceDao.isIdExist(id)) {
			throw new Exception("Province not found!");
		}
	}
	
	public void valIdNotNull(Province province) throws Exception {
		if(province.getId().isEmpty()) {
			throw new Exception("Province ID can't empty!");
		}
	}

    public List<Province> findAll(){
        return provinceDao.findAll();
    }
    
    public Province findById(String id) {
		return provinceDao.findOne(id);
	}
	
	public Province findByCode(String code) {
		return provinceDao.findByCode(code);
	}
	
	public void valBkNotNull(Province province) throws Exception {
		if(province.getCountry().getId().isEmpty()) {
			throw new Exception("Country can't empty!");
		}
		if(province.getCode().isEmpty()) {
			throw new Exception("Code can't empty!");
		}
	}
	
	public void valBkNotExist(Province province) throws Exception {
		if(provinceDao.isBkExist(province.getCode(), province.getCountry().getId())) {
			throw new Exception("Province already exists!");
		}
	}
	
	public void valBkNotChange(Province province) throws Exception {
		String code = findById(province.getId()).getCode();
		String company = findById(province.getId()).getCountry().getId();
		
		if(!(province.getCode().equals(code) && province.getCountry().getId().equals(company))) {
			throw new Exception("BK can't be changed!");
		}
	}
	
	public void valNonBk(Province province) throws Exception {
		if(province.getName().isEmpty()) {
			throw new Exception("Province name can't empty!");
		}
	}
	
	public void insert(Province province) throws Exception {
		valBkNotNull(province);
		valBkNotExist(province);
		valNonBk(province);
		provinceDao.create(province);
	}
	
	public void update(Province province) throws Exception {
		valIdNotNull(province);
		valIdExist(province.getId());
		valBkNotNull(province);
		valBkNotChange(province);
		valNonBk(province);
		provinceDao.update(province);
	}
	
	public void delete(String id) throws Exception {
		valIdExist(id);
		provinceDao.deleteById(id);
	}

}
