package com.demo.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.LovTypeDao;
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
	
    public void save(LovType lovType) throws Exception {
    	lovType.setCreatedAt(new Timestamp(System.currentTimeMillis()));
    	valBkNotNull(lovType);
		valBkNotExist(lovType);
		valNonBk(lovType);
		lovTypeDao.create(lovType);
    }
    
    public void update(LovType lovType) throws Exception {
    	lovType.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
    	valIdNotNull(lovType);
		valIdExist(lovType.getId());
		valBkNotNull(lovType);
		valBkNotChange(lovType);
		valNonBk(lovType);
		valCreatedNotChange(lovType);
		lovTypeDao.update(lovType);
    }

    public void delete(String id) throws Exception {
		valIdExist(id);
		lovTypeDao.deleteById(id);
	}

	private void valIdExist(String id)throws Exception{
		if(!lovTypeDao.isIdExist(id)) {
			throw new Exception("Data tidak ada");
		}
	}
	
	private void valIdNotNull(LovType lovType)throws Exception {
		if(lovType.getId()==null) {
			throw new Exception("Id tidak boleh kosong");
		}
	}
	
	private void valNonBk(LovType lovType)throws Exception{
		StringBuilder sb=new StringBuilder();
		int error=0;

		if(lovType.getName()==null) {
			sb.append("City cannot be null !");
			error++;
		}
		if(lovType.getVersion() == null) {
			sb.append("Version cannot be null !");
			error++;
		}
				
		if(error>0) {
			throw new Exception(sb.toString());
		}
	}
	
	private void valBkNotExist(LovType lovType)throws Exception{
		if(lovTypeDao.isBkExist(lovType)) {
			throw new Exception("Data sudah ada");
		}
	}	
	
	private void valBkNotChange(LovType lovType)throws Exception{
		LovType tempLovType=findById(lovType.getId());
		if(!tempLovType.getCode().equals(lovType.getCode())) {
			throw new Exception("BK tidak boleh berubah");
		}
	}
	
	private void valBkNotNull(LovType lovType) throws Exception{
		if(lovType.getCode()==null) {
			throw new Exception("Bk tidak boleh kosong");
		}
	}
	
	private void valCreatedNotChange(LovType lovType)throws Exception {
		LovType tempLovType=findById(lovType.getId());
		
		if(tempLovType.getCreatedAt()!=lovType.getCreatedAt() || tempLovType.getCreatedBy()!=lovType.getCreatedBy()) {
			throw new Exception("created tidak boleh berubah");
		}
	}
}
