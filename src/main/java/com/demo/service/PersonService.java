package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.PersonDao;
import com.demo.model.Person;

@Service
public class PersonService {
	@Autowired
	private PersonDao personDao;
	
	public List<Person> findAllPerson(){
        return personDao.findAll();
    }
	
	public Person findPersonById(String id){
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
		if(person.getTenant() == null) {
			throw new Exception("Must Have Tenant");
		}
		if(person.getFirstName() == null) {
			throw new Exception("Must Have First Name");
		}
		if(person.getLastName() == null) {
			throw new Exception("Must Have Last Name");
		}
		if(person.getBirthDate() == null) {
			throw new Exception("Must Have Birth Date");
		}
		if(person.getPhone() == null) {
			throw new Exception("Must Have Phone Number");
		}
	}
	
	private void valNonBk(Person person) throws Exception {
		if(!person.getEmail().matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")) {
			throw new Exception("Illegal Email Format");
		}
		if(person.getCountry().getId() == null) {
			throw new Exception("Must Choose Country");
		}
		if(person.getLovPtyp().getId() == null) {
			throw new Exception("Must Have Type");
		}
		if(person.getLovGndr().getId() == null) {
			throw new Exception("Must Have Gender");
		}
		if(person.getLovRlgn().getId() == null) {
			throw new Exception("Must Have Religion");
		}
		if(person.getLovMars().getId() == null) {
			throw new Exception("Must Have Status");
		}
		if(person.getCreatedBy() == null) {
			throw new Exception("Must Have Creator");
		}
		if(person.getCreatedAt() == null) {
			throw new Exception("Must Have Time Created");
		}
	}
}
