package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.PersonFamilyDao;
import com.demo.model.PersonFamily;

@Service
public class PersonFamilyService {
	
	@Autowired
	private PersonFamilyDao pfDao;
	
	public void valIdExist(String id) throws Exception {
		if(!pfDao.isIdExist(id)) {
			throw new Exception("Person Family not found!");
		}
	}
	
	public void valIdNotNull(PersonFamily pf) throws Exception {
		if(pf.getId().isEmpty()) {
			throw new Exception("Person Family ID can't empty!");
		}
	}
	
	public List<PersonFamily> findAll() {
		return pfDao.findAll();
	}
	
	public PersonFamily findById(String id) {
		return pfDao.findOne(id);
	}
	
	public void valBkNotNull(PersonFamily pf) throws Exception {
		if(pf.getPerson().getId().isEmpty()) {
			throw new Exception("Person can't empty!");
		}
		if(pf.getLovFamr().getId().isEmpty()) {
			throw new Exception("LOV Family Relation can't empty!");
		}
		if(pf.getName().isEmpty()) {
			throw new Exception("Name can't empty!");
		}
	}
	
	public void valBkNotExist(PersonFamily pf) throws Exception {
		if(pfDao.isBkExist(pf.getPerson().getId(), pf.getLovFamr().getId(), pf.getName())) {
			throw new Exception("Person Family already exists!");
		}
	}
	
	public void valBkNotChange(PersonFamily pf) throws Exception {
		String person = findById(pf.getId()).getPerson().getId();
		String lovFamr = findById(pf.getId()).getLovFamr().getId();
		String name = findById(pf.getId()).getName();
		
		if(!(pf.getPerson().getId().equals(person) && pf.getLovFamr().getId().equals(lovFamr) && pf.getName().equals(name))) {
			throw new Exception("BK can't be changed!");
		}
	}
	
	public void valNonBk(PersonFamily pf) throws Exception {
		if(pf.getLovGndr().getId().isEmpty()) {
			throw new Exception("LOV Gender can't empty!");
		}
		if(pf.getBirthDate() == null) {
			throw new Exception("DOB can't empty!");
		}
		if(pf.getLovEdul().getId().isEmpty()) {
			throw new Exception("LOV Education Level can't empty!");
		}
		
	}
	
	public void insert(PersonFamily pf) throws Exception {
		valBkNotNull(pf);
		valBkNotExist(pf);
		valNonBk(pf);
		pfDao.create(pf);
	}
	
	public void update(PersonFamily pf) throws Exception {
		valIdNotNull(pf);
		valIdExist(pf.getId());
		valBkNotNull(pf);
		valBkNotChange(pf);
		valNonBk(pf);
		pfDao.update(pf);
	}
	
	public void delete(String id) throws Exception {
		valIdExist(id);
		pfDao.deleteById(id);
	}

}
