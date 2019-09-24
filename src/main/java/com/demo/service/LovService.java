package com.demo.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.LovDao;
import com.demo.exception.ValidationException;
import com.demo.model.Lov;
import com.demo.model.LovType;

@Service
public class LovService {

	@Autowired
	private LovDao lovDao;
	
	public List<Lov> findAll(Integer offset,Integer limit){
        return lovDao.findAll(offset, limit);
    }
	
	public Lov findById(String id){
        return lovDao.findOne(id);
    }
	
	public Lov findByBk(String lovTypeId,String keyData){
		Lov lov=new Lov();
		LovType lovType=new LovType();
		lovType.setId(lovTypeId);
		lov.setLovType(lovType);
		lov.setKeyData(keyData);
        return lovDao.findByBk(lov);
    }
	
	public void save(Lov lov) throws ValidationException {
		lov.setCreatedAt(new Timestamp(System.currentTimeMillis()));
    	valBkNotNull(lov);
		valBkNotExist(lov);
		valNonBk(lov);
		lovDao.create(lov);
	}
	
	public void update(Lov lov) throws ValidationException {
		lov.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
		valIdNotNull(lov);
		valIdExist(lov.getId());
		valBkNotNull(lov);
		valBkNotChange(lov);
		valNonBk(lov);
		valCreatedNotChange(lov);
		lovDao.update(lov);
	}

	public void delete(String id) throws ValidationException {
		valIdExist(id);
		lovDao.deleteById(id);
	}

	private void valIdExist(String id)throws ValidationException{
		if(!lovDao.isIdExist(id)) {
			throw new ValidationException("Data tidak ada");
		}
	}
	
	private void valIdNotNull(Lov lov)throws ValidationException {
		if(lov.getId().isEmpty()) {
			throw new ValidationException("Id tidak boleh kosong");
		}
	}
	
	private void valNonBk(Lov lov)throws ValidationException{
		StringBuilder sb=new StringBuilder();
		int error=0;

		if(lov.getValData().isEmpty()) {
			sb.append("Value Data cannot be null !");
			error++;
		}
		if(lov.getIsDisableable() == null) {
			sb.append("isDisable cannot be null !");
			error++;
		}
		if(lov.getIsActive() == null) {
			sb.append("IsActive cannot be null !");
			error++;
		}
		if(lov.getVersion() == null) {
			sb.append("Version cannot be null !");
			error++;
		}
				
		if(error>0) {
			throw new ValidationException(sb.toString());
		}
	}
	
	private void valBkNotExist(Lov lov)throws ValidationException{
		if(lovDao.isBkExist(lov)) {
			throw new ValidationException("Data sudah ada");
		}
	}	
	
	private void valBkNotChange(Lov lov)throws ValidationException{
		Lov tempLov=findById(lov.getId());
		if(!tempLov.getLovType().getId().equals(lov.getLovType().getId()) || tempLov.getKeyData().equals(lov.getKeyData())) {
			throw new ValidationException("BK tidak boleh berubah");
		}
	}
	
	private void valBkNotNull(Lov lov) throws ValidationException{
		if(lov.getLovType().getId()==null || lov.getKeyData()==null) {
			throw new ValidationException("Bk tidak boleh kosong");
		}
	}
	
	private void valCreatedNotChange(Lov lov)throws ValidationException {
		Lov tempLov=findById(lov.getId());
		
		if(tempLov.getCreatedAt()!=lov.getCreatedAt() || tempLov.getCreatedBy()!=lov.getCreatedBy()) {
			throw new ValidationException("created tidak boleh berubah");
		}
	}
}
