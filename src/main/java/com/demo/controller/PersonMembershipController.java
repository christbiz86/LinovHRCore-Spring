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

import com.demo.model.PersonMembership;
import com.demo.service.PersonMembershipService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@RequestMapping({"/api/v1"})
public class PersonMembershipController {
	@Autowired
	private PersonMembershipService personMembershipService;
	
	@GetMapping(value = "person-memberships")
	@Transactional
	public ResponseEntity<?> getAllPersonMember() {
		try {
			List<PersonMembership> persMemList = personMembershipService.findAll();
			return ResponseEntity.ok(persMemList);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping(value = "/person-membership/{id}")
	@Transactional
	public ResponseEntity<?> getPersMemberById(@PathVariable String id) {
		try {
			PersonMembership persMem = personMembershipService.findById(id);
			return ResponseEntity.ok(persMem);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		
	}
	
	@PostMapping(value = "/person-membership")
	@Transactional
	public ResponseEntity<?> submit(@RequestBody PersonMembership personMembership) throws Exception {
		try {
			personMembershipService.save(personMembership);
			return ResponseEntity.ok("Data Have Successfully Saved");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping(value = "/person-membership")
	@Transactional
	public ResponseEntity<?> update(@RequestBody PersonMembership personMembership) throws Exception {
		try {
			personMembershipService.update(personMembership);
			return ResponseEntity.ok("Data Have Successfully Updated");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@DeleteMapping(value = "/person-membership/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable String id) throws Exception {
		try {
			personMembershipService.delete(id);
			return ResponseEntity.ok("Data Have Successfully Deleted");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
}
