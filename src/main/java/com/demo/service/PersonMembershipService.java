package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.PersonMembershipDao;
import com.demo.model.PersonMembership;

@Service
public class PersonMembershipService {
	@Autowired
	private PersonMembershipDao personMembershipDao;
	
	public List<PersonMembership> findAll() {
		return personMembershipDao.findAll();
	}
	
	public PersonMembership findById(String id) {
		return personMembershipDao.findOne(id);
	}
	
	public PersonMembership findByBk(String personId, String lovId) {
		return personMembershipDao.findByBk(personId, lovId);
	}
	
	private void valIdExist(String id)throws Exception{
		if(!personMembershipDao.isIdExist(id)) {
			throw new Exception("Data is Not Found");
		}
	}
	
	private void valIdNotNull(PersonMembership personMembership)throws Exception {
		
		if(personMembership.getId() == null || personMembership.getId().isEmpty()) {
			throw new Exception("Id Cannot be empty");
		}
	}
	
	private void valNonBk(PersonMembership personMembership) throws Exception {
		if(personMembership.getAccNumber().isEmpty()) {
			throw new Exception("Acc Number cannot be empty");
		}
	}
	
	private void valBkNotExist(PersonMembership personMembership)throws Exception{
		if(personMembershipDao.isBkExist(personMembership.getPerson().getId(), personMembership.getLovMemberType().getId())) {
			throw new Exception("Data already exist");
		}
	}
	
	private void valBkNotChange(PersonMembership personMembership)throws Exception{
		String person = findById(personMembership.getId()).getPerson().getId();
		String lov = findById(personMembership.getId()).getLovMemberType().getId();
		if(!personMembership.getPerson().getId().equals(person)) {
			throw new Exception("Person is cannot be changed");
		}
		if(!personMembership.getLovMemberType().getId().equals(lov)) {
			throw new Exception("Membership Type is cannot be changed");
		}
	}
	
	private void valBkNotNull(PersonMembership personMembership) throws Exception{
		if(personMembership.getPerson() == null || personMembership.getPerson().getId().isEmpty()) {
			throw new Exception("Person cannot be empty");
		}
		if(personMembership.getLovMemberType() == null || personMembership.getLovMemberType().getId().isEmpty()) {
			throw new Exception("Membership Type cannot be empty");
		}
	}
	
	public void save(PersonMembership personMembership) throws Exception {
		valBkNotNull(personMembership);
		valNonBk(personMembership);
		valBkNotExist(personMembership);
		personMembershipDao.create(personMembership);
	}
	
	public void update(PersonMembership personMembership) throws Exception {
		valIdNotNull(personMembership);
		valIdExist(personMembership.getId());
		valBkNotNull(personMembership);
		valBkNotChange(personMembership);
		valNonBk(personMembership);
		personMembershipDao.update(personMembership);
	}
	
	public void delete(String id) throws Exception {
		personMembershipDao.deleteById(id);
	}
}
