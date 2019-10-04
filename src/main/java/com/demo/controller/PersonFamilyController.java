package com.demo.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.PersonFamily;
import com.demo.service.PersonFamilyService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@Transactional
@RequestMapping({ "/api/v1" })
public class PersonFamilyController {
	
	@Autowired
	private PersonFamilyService pfService;
	
	@GetMapping(value = "/person-families")
	@Transactional
	public ResponseEntity<?> findAll() throws Exception {
		try {
			List<PersonFamily> list = pfService.findAll();
			return new ResponseEntity<List<PersonFamily>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@GetMapping(value = "/person-family/{id}")
	@Transactional
	public ResponseEntity<?> findById(@PathVariable String id) throws Exception {
		try {
			PersonFamily pf = pfService.findById(id);
			return new ResponseEntity<PersonFamily>(pf, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@PostMapping(value = "/person-family")
	@Transactional
	public ResponseEntity<?> insert(@RequestBody PersonFamily pf) throws Exception {
		try {
			pfService.insert(pf);
			return ResponseEntity.ok("Insert success with Person Family name: "+pf.getName());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping(value = "/person-family")
	@Transactional
	public ResponseEntity<?> update(@RequestBody PersonFamily pf) throws Exception {
		try {
			pfService.update(pf);
			return ResponseEntity.ok("Update success with Person Family ID: "+pf.getId());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@DeleteMapping(value = "/person-family/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable String id) throws Exception {
		try {
			pfService.delete(id);
			return ResponseEntity.ok("Delete success with Person Family ID: "+id);
		} catch (Exception e) {
		    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

}
