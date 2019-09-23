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

    public List<Province> findAll(){
        return provinceDao.findAll();
    }

}
