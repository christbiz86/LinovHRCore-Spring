package com.demo.service;

import com.demo.dao.CostcenterDao;
import com.demo.model.Costcenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CostcenterService {

    @Autowired
    private CostcenterDao costcenterDao;

    public List<Costcenter> findAll(){
        return costcenterDao.findAll();
    }

    public List<Costcenter> findById(UUID id){
        return costcenterDao.findById(id);
    }

}
