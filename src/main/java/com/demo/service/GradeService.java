package com.demo.service;

import com.demo.dao.CompanyDao;
import com.demo.dao.GradeDao;
import com.demo.model.Company;
import com.demo.model.Grade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.ValidationException;
import java.util.List;

@Service
public class GradeService {

    @Autowired
    private GradeDao gradeDao;

    @Autowired
    private CompanyDao companyDao;

    public List<Grade> findall(){
        return gradeDao.findAll();
    }

    public List<Grade> findById(String id){
        return gradeDao.findById(id);
    }

    public List<Grade> findByCode(String code){
        return gradeDao.findByCode(code);
    }

    @Transactional
    public void insert(Grade grade) throws ValidationException{
        gradeValidation(grade);
        gradeDao.insert(grade);
    }

    private void gradeValidation(Grade grade) throws ValidationException{
        List<Company> companyId = companyDao.findById(grade.getCompany().getId());
        if(companyId.isEmpty()) {
            throw new ValidationException("Company ID not found or empty!");
        }
        if(grade.getName() == null){
            throw new ValidationException("Name is mandatory!");
        }
        if(grade.getCode() == null){
            throw new ValidationException("Code is mandatory!");
        }
        if(grade.getOrdinal() == null){
            throw new ValidationException("Ordinal is mandatory!");
        }
        if(grade.getCreatedBy() == null){
            throw new ValidationException("CreatedBy is mandatory!");
        }
    }

}
