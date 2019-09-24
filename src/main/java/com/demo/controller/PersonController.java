package com.demo.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.Person;
import com.demo.service.PersonService;

@RestController
@Controller
@Transactional
@RequestMapping({"/api/v1"})
public class PersonController {
	@Autowired
	private PersonService personService;
	
	@GetMapping(value = "/persons")
    public ResponseEntity<?> getAllPerson() {
        try {
			return ResponseEntity.ok(personService.findAllPerson());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
	@GetMapping(value = "/person/{id}")
    public ResponseEntity<?> getPersonById(@PathVariable String id) {
        try {
			return ResponseEntity.ok(personService.findPersonById(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
	@PostMapping(value = "/person")
    public ResponseEntity<?> savePerson(@RequestBody Person person) {
        try {
        	personService.savePerson(person);
			return ResponseEntity.status(HttpStatus.CREATED).body("Data Have Succesfully Saved");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
}