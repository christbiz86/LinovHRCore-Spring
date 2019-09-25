package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.exception.ValidationException;
import com.demo.model.Duty;
import com.demo.service.DutyService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@RequestMapping(value = "/api/v1")
public class DutyController {
	
	@Autowired
	private DutyService dutyService;
	
	@GetMapping(value = "/lov/duties")
	@Transactional
	public ResponseEntity<?> findAll() throws Exception {
		try {
			List<Duty> list = dutyService.findAll();
			return new ResponseEntity<List<Duty>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Retrieve failed!");
		}
	}
	
	@GetMapping(value = "/duty/{id}")
	@Transactional
	public ResponseEntity<?> findById(@PathVariable String id) throws Exception {
		try {
			Duty duty = dutyService.findById(id);
			return new ResponseEntity<Duty>(duty, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Retrieve failed!");
		}
	}
	
	@PostMapping(value = "/duty")
	@Transactional
	public ResponseEntity<?> insert(@RequestBody Duty duty) throws Exception {
		try {
			dutyService.insert(duty);
			return ResponseEntity.ok("Insert success!");
		} catch (ValidationException ve) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ve.getMessages());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping(value = "/duty")
	@Transactional
	public ResponseEntity<?> update(@RequestBody Duty duty) throws Exception {
		try {
			dutyService.update(duty);
			return ResponseEntity.ok("Update success with job responsibility ID: "+duty.getId());
		} catch (ValidationException ve) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ve.getMessages());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@DeleteMapping(value = "/duty/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable String id) throws Exception {
		try {
			dutyService.delete(id);
			return ResponseEntity.ok("Delete success with ID: "+id);
		} catch (Exception e) {
		    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Delete failed!");
		}
	}

}
