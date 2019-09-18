package com.demo.service;

import com.demo.dao.GradeDao;
import com.demo.model.Grade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService {

    @Autowired
    private GradeDao gradeDao;

    public List<Grade> findall(){
        return gradeDao.findAll();
    }

}
