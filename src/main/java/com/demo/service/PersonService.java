package com.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.PersonDao;
import com.demo.model.Person;

@Service
public class PersonService {
	@Autowired
	private PersonDao personDao;
	
	@Transactional
	public List<Person> findAllPerson() throws Exception{
        return personDao.findAll();
    }
}
