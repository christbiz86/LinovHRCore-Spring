package com.demo.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.LovDao;
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
	
	public void save(Lov lov) throws Exception {
		lov.setCreatedAt(new Timestamp(System.currentTimeMillis()));
    	valBkNotNull(lov);
		valBkNotExist(lov);
		valNonBk(lov);
		lovDao.create(lov);
	}
	
	public void update(Lov lov) throws Exception {
		lov.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
		valIdNotNull(lov);
		valIdExist(lov.getId());
		valBkNotNull(lov);
		valBkNotChange(lov);
		valNonBk(lov);
		valCreatedNotChange(lov);
		lovDao.update(lov);
	}

	public void delete(String id) throws Exception {
		valIdExist(id);
		lovDao.deleteById(id);
	}

	private void valIdExist(String id)throws Exception{
		if(!lovDao.isIdExist(id)) {
			throw new Exception("Data tidak ada");
		}
	}
	
	private void valIdNotNull(Lov lov)throws Exception {
		if(lov.getId().isEmpty()) {
			throw new Exception("Id tidak boleh kosong");
		}
	}
	
	private void valNonBk(Lov lov)throws Exception{
		if(lov.getValData().isEmpty()) {
			throw new Exception("Value Data cannot be null !");
		}
		if(lov.getIsDisableable() == null) {
			throw new Exception("isDisable cannot be null !");
		}
		if(lov.getIsActive() == null) {
			throw new Exception("IsActive cannot be null !");
		}
	}
	
	private void valBkNotExist(Lov lov)throws Exception{
		if(lovDao.isBkExist(lov)) {
			throw new Exception("Data sudah ada");
		}
	}	
	
	private void valBkNotChange(Lov lov)throws Exception{
		Lov tempLov=findById(lov.getId());
		if(!tempLov.getLovType().getId().equals(lov.getLovType().getId()) || tempLov.getKeyData().equals(lov.getKeyData())) {
			throw new Exception("BK tidak boleh berubah");
		}
	}
	
	private void valBkNotNull(Lov lov) throws Exception{
		if(lov.getLovType().getId()==null || lov.getKeyData()==null) {
			throw new Exception("Bk tidak boleh kosong");
		}
	}
	
	private void valCreatedNotChange(Lov lov)throws Exception {
		Lov tempLov=findById(lov.getId());
		
		if(tempLov.getCreatedAt()!=lov.getCreatedAt() || tempLov.getCreatedBy()!=lov.getCreatedBy()) {
			throw new Exception("created tidak boleh berubah");
		}
	}
}
