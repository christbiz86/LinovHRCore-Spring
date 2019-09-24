package com.demo.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.LovTypeDao;
import com.demo.exception.ValidationException;
import com.demo.model.LovType;

@Service
public class LovTypeService {

	@Autowired
	private LovTypeDao lovTypeDao;
	
	public List<LovType> findAll(){
        return lovTypeDao.findAll();
    }
	
	public List<LovType> findAll(Integer offset,Integer limit){
        return lovTypeDao.findAll(offset, limit);
    }
	
	public LovType findById(String id){
        return lovTypeDao.findOne(id);
    }
	
	public LovType findByBk(String code){
		LovType lovType=new LovType();
		lovType.setCode(code);
        return lovTypeDao.findByBk(lovType);
    }
	
    public void save(LovType lovType) throws ValidationException {
    	lovType.setCreatedAt(new Timestamp(System.currentTimeMillis()));
    	valBkNotNull(lovType);
		valBkNotExist(lovType);
		valNonBk(lovType);
		lovTypeDao.create(lovType);
    }
    
    public void update(LovType lovType) throws ValidationException {
    	lovType.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
    	valIdNotNull(lovType);
		valIdExist(lovType.getId());
		valBkNotNull(lovType);
		valBkNotChange(lovType);
		valNonBk(lovType);
		valCreatedNotChange(lovType);
		lovTypeDao.update(lovType);
    }

    public void delete(String id) throws ValidationException {
		valIdExist(id);
		lovTypeDao.deleteById(id);
	}

	private void valIdExist(String id)throws ValidationException{
		if(!lovTypeDao.isIdExist(id)) {
			throw new ValidationException("Data tidak ada");
		}
	}
	
	private void valIdNotNull(LovType lovType)throws ValidationException {
		if(lovType.getId().isEmpty()) {
			throw new ValidationException("Id tidak boleh kosong");
		}
	}
	
	private void valNonBk(LovType lovType)throws ValidationException{
		StringBuilder sb=new StringBuilder();
		int error=0;

		if(lovType.getName().isEmpty()) {
			sb.append("City cannot be null !");
			error++;
		}
		if(lovType.getVersion() == null) {
			sb.append("Version cannot be null !");
			error++;
		}
				
		if(error>0) {
			throw new ValidationException(sb.toString());
		}
	}
	
	private void valBkNotExist(LovType lovType)throws ValidationException{
		if(lovTypeDao.isBkExist(lovType)) {
			throw new ValidationException("Data sudah ada");
		}
	}	
	
	private void valBkNotChange(LovType lovType)throws ValidationException{
		LovType tempLovType=findById(lovType.getId());
		if(!tempLovType.getCode().equals(lovType.getCode())) {
			throw new ValidationException("BK tidak boleh berubah");
		}
	}
	
	private void valBkNotNull(LovType lovType) throws ValidationException{
		if(lovType.getCode().isEmpty()) {
			throw new ValidationException("Bk tidak boleh kosong");
		}
	}
	
	private void valCreatedNotChange(LovType lovType)throws ValidationException {
		LovType tempLovType=findById(lovType.getId());
		
		if(tempLovType.getCreatedAt()!=lovType.getCreatedAt() || tempLovType.getCreatedBy()!=lovType.getCreatedBy()) {
			throw new ValidationException("created tidak boleh berubah");
		}
	}
}
