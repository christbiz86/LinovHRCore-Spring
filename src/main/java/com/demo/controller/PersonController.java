package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.service.PersonService;

@RestController
@Controller
@RequestMapping({"/api/v1"})
public class PersonController {
	@Autowired
	private PersonService personService;
	
	@GetMapping(value = "/persons")
    public ResponseEntity<?> getAllPerson(){
        try {
			return ResponseEntity.ok(personService.findAllPerson());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
}
