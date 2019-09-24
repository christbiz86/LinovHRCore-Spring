package com.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.PersonDao;
import com.demo.exception.ValidationException;
import com.demo.model.Person;

@Service
public class PersonService {
	@Autowired
	private PersonDao personDao;
	
	public List<Person> findAllPerson() throws Exception{
        return personDao.findAll();
    }
	
	public Person findPersonById(String id) throws Exception{
		if(personDao.isIdExist(id)) {
			return personDao.findOne(id);
		}else {
			return new Person();
		}
    }
	
	public void savePerson(Person person) throws Exception{
		valBkNotNull(person);
		valNonBk(person);
		personDao.create(person);
	}
	
	private void valBkNotNull(Person person) throws Exception {
		List<String> listErr = new ArrayList<String>();
		if(person.getTenant() == null) {
			listErr.add("Must Have Tenant");
		}
		if(person.getFirstName() == null) {
			listErr.add("Must Have First Name");
		}
		if(person.getLastName() == null) {
			listErr.add("Must Have Last Name");
		}
		if(person.getBirthDate() == null) {
			listErr.add("Must Have Birth Date");
		}
		if(person.getPhone() == null) {
			listErr.add("Must Have Phone Number");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	private void valNonBk(Person person) throws Exception {
		List<String> listErr = new ArrayList<String>();
		if(!person.getEmail().matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")) {
			listErr.add("Illegal Email Format");
		}
		if(person.getCountry().getId() == null) {
			listErr.add("Must Choose Country");
		}
		if(person.getLovPtyp().getId() == null) {
			listErr.add("Must Have Type");
		}
		if(person.getLovGndr().getId() == null) {
			listErr.add("Must Have Gender");
		}
		if(person.getLovRlgn().getId() == null) {
			listErr.add("Must Have Religion");
		}
		if(person.getLovMars().getId() == null) {
			listErr.add("Must Have Status");
		}
		if(person.getCreatedBy() == null) {
			listErr.add("Must Have Creator");
		}
		if(person.getCreatedAt() == null) {
			listErr.add("Must Have Time Created");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
}