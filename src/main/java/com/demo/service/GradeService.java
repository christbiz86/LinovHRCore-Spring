package com.demo.service;

import java.sql.Timestamp;
import java.util.List;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.CompanyDao;
import com.demo.dao.GradeDao;
import com.demo.model.Company;
import com.demo.model.Grade;

@Service
public class GradeService {

    @Autowired
    private GradeDao gradeDao;

    @Autowired
    private CompanyDao companyDao;

    public List<Grade> findAll(){
        return gradeDao.findAll();
    }

    public Grade findById(String id){
        return gradeDao.findById(id);
    }

    public List<Grade> findByCode(String code){
        return gradeDao.findByCode(code);
    }

    public void insert(Grade grade) throws ValidationException{
        gradeValidation(grade);
        gradeDao.save(grade);
    }

    public void update(Grade grade) throws ValidationException{
        grade.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        gradeValidId(grade);
        gradeValidation(grade);
        gradeDao.save(grade);
    }

    public void delete(String id) throws ValidationException{
        gradeDao.delete(id);
    }

    private void gradeValidId(Grade grade) throws ValidationException{
        if(grade.getId() == null){
            throw new ValidationException("Grade ID null!");
        } else if(gradeDao.findById(grade.getId()) == null){
            throw new ValidationException("Grade ID not found!");
        }
    }

    private void gradeValidation(Grade grade) throws ValidationException{
        Company companyId = companyDao.findOne(grade.getCompany().getId());
        if(companyId == null) {
            throw new ValidationException("Company ID not found or empty!");
        }
        if(grade.getName() == null){
            throw new ValidationException("Object Name doesn't exist!");
        }
        if(grade.getName().isEmpty()){
            throw new ValidationException("Name is empty!");
        }
        if(grade.getCode() == null){
            throw new ValidationException("Object Code doesn't exist!");
        }
        if(grade.getCode().isEmpty()){
            throw new ValidationException("Code is empty!");
        }
        if(grade.getOrdinal() == null){
            throw new ValidationException("Object Ordinal doesn't exist!");
        }
        if(grade.getCreatedBy() == null){
            throw new ValidationException("Object CreatedBy doesn't exist!");
        }
        if(grade.getCreatedBy().isEmpty()){
            throw new ValidationException("CreatedBy is empty!");
        }
    }

}
