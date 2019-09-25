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
import com.demo.model.JobResponsibility;
import com.demo.service.JobResponsibilityService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@RequestMapping(value = "/api/v1")
public class JobResponsibilityController {
	
	@Autowired
	private JobResponsibilityService jobRespService;
	
	@GetMapping(value = "/lov/job-responsibilities")
	@Transactional
	public ResponseEntity<?> findAll() throws Exception {
		try {
			List<JobResponsibility> list = jobRespService.findAll();
			return new ResponseEntity<List<JobResponsibility>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Retrieve failed!");
		}
	}
	
	@GetMapping(value = "/job-responsibility/{id}")
	@Transactional
	public ResponseEntity<?> findById(@PathVariable String id) throws Exception {
		try {
			JobResponsibility jobResponsibility = jobRespService.findById(id);
			return new ResponseEntity<JobResponsibility>(jobResponsibility, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Retrieve failed!");
		}
	}
	
	@PostMapping(value = "/job-responsibility")
	@Transactional
	public ResponseEntity<?> insert(@RequestBody JobResponsibility jobResponsibility) throws Exception {
		try {
			jobRespService.insert(jobResponsibility);
			return ResponseEntity.ok("Insert success!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping(value = "/job-responsibility")
	@Transactional
	public ResponseEntity<?> update(@RequestBody JobResponsibility jobResponsibility) throws Exception {
		try {
			jobRespService.update(jobResponsibility);
			return ResponseEntity.ok("Update success with job responsibility ID: "+jobResponsibility.getId());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@DeleteMapping(value = "/job-responsibility/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable String id) throws Exception {
		try {
			jobRespService.delete(id);
			return ResponseEntity.ok("Delete success with ID: "+id);
		} catch (Exception e) {
		    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Delete failed!");
		}
	}

}
