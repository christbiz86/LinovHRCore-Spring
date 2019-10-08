package com.demo.service;

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
    	valBkNotNull(personAddress);
		valBkNotExist(personAddress);
		valNonBk(personAddress);
    	personAddressDao.create(personAddress);
    }
    
    public void update(PersonAddress personAddress) throws Exception {
    	valIdNotNull(personAddress);
		valIdExist(personAddress.getId());
		valBkNotNull(personAddress);
		valBkNotChange(personAddress);
		valNonBk(personAddress);
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
		if(personAddress.getCity().getId().isEmpty()) {
			throw new Exception("City cannot be null !");
		}
		if(personAddress.getLovRsty().getId().isEmpty()) {
			throw new Exception("cannot be null!");
		}
		if(personAddress.getLovRsow().getId().isEmpty()) {
			throw new Exception("cannot be null!");
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
		if(personAddress.getPerson().getId().isEmpty() || personAddress.getAddress().isEmpty()) {
			throw new Exception("Kode tidak boleh kosong");
		}
	}
	
}