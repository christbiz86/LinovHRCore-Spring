package com.demo.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.PersonAddressDao;
import com.demo.model.PersonAddress;

@Service
public class PersonAddressService {

    @Autowired
    private PersonAddressDao personAddressDao;

    public List<PersonAddress> findAll(Integer offset, Integer limit){
        return personAddressDao.findAll(offset,limit);
    }
    
    public PersonAddress findById(String id){
        return personAddressDao.findOne(id);
    }
    
    public List<PersonAddress> findByPersonId(String id){
        return personAddressDao.findByPersonId(id);
    }
    
    public void save(PersonAddress personAddress) throws Exception {
    	personAddress.setCreatedAt(new Timestamp(System.currentTimeMillis()));
    	valBkNotNull(personAddress);
		valBkNotExist(personAddress);
		valNonBk(personAddress);
    	personAddressDao.create(personAddress);
    }
    
    public void update(PersonAddress personAddress) throws Exception {
    	personAddress.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
    	valIdNotNull(personAddress);
		valIdExist(personAddress.getId());
		valBkNotNull(personAddress);
		valBkNotChange(personAddress);
		valNonBk(personAddress);
		valCreatedNotChange(personAddress);
    	personAddressDao.update(personAddress);
    }
    
	public void delete(String id) throws Exception {
		valIdExist(id);
		personAddressDao.deleteById(id);
	}
    
	private void valIdExist(String id)throws Exception{
		if(!personAddressDao.isIdExist(id)) {
			throw new Exception("Data tidak ada");
		}
	}
	
	private void valIdNotNull(PersonAddress personAddress)throws Exception {
		if(personAddress.getId().isEmpty()) {
			throw new Exception("Id tidak boleh kosong");
		}
	}
	
	private void valNonBk(PersonAddress personAddress)throws Exception{
		StringBuilder sb=new StringBuilder();
		int error=0;

		if(personAddress.getCity().getId()==null) {
			sb.append("City cannot be null !");
			error++;
		}
		if(personAddress.getLovRsty().getId()==null) {
			sb.append("cannot be null!");
			error++;
		}
		if(personAddress.getLovRsow().getId()==null) {
			sb.append("cannot be null!");
			error++;
		}
		if(personAddress.getVersion() == null) {
			sb.append("Version cannot be null !");
			error++;
		}
				
		if(error>0) {
			throw new Exception(sb.toString());
		}
	}
	
	private void valBkNotExist(PersonAddress personAddress)throws Exception{
		if(personAddressDao.isBkExist(personAddress)) {
			throw new Exception("Data sudah ada");
		}
	}	
	
	private void valBkNotChange(PersonAddress personAddress)throws Exception{
		PersonAddress tempPersonAddress=findById(personAddress.getId());
		if(!tempPersonAddress.getPerson().getId().equals(personAddress.getPerson().getId()) || !tempPersonAddress.getAddress().equals(personAddress.getAddress())) {
			throw new Exception("BK tidak boleh berubah");
		}
	}
	
	private void valBkNotNull(PersonAddress personAddress) throws Exception{
		if(personAddress.getPerson().getId()==null || personAddress.getAddress()==null) {
			throw new Exception("Kode tidak boleh kosong");
		}
	}
	
	private void valCreatedNotChange(PersonAddress personAddress)throws Exception {
		PersonAddress tempPersonAddress=findById(personAddress.getId());
		if(!tempPersonAddress.getCreatedAt().equals(personAddress.getCreatedAt()) || !tempPersonAddress.getCreatedBy().equals(personAddress.getCreatedBy())) {
			throw new Exception("created tidak boleh berubah");
		}
	}
}