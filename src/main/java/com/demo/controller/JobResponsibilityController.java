package com.demo.controller;

import java.util.List;

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

import com.demo.model.JobGrade;
import com.demo.model.JobResponsibility;
import com.demo.service.JobResponsibilityService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@RequestMapping(value = "/api/v1")
public class JobResponsibilityController {
	
	@Autowired
	private JobResponsibilityService jobRespService;
	
	@GetMapping(value = "/lov/jobresponsibilities")
	public ResponseEntity<?> findAll() throws Exception {
		try {
			List<JobResponsibility> list = jobRespService.findAll();
			return new ResponseEntity<List<JobResponsibility>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Retrieve failed!");
		}
	}
	
	@GetMapping(value = "/jobresponsibility/{uuid}")
	public ResponseEntity<?> findById(@PathVariable String uuid) throws Exception {
		try {
			JobResponsibility jobResponsibility = jobRespService.findById(uuid);
			return new ResponseEntity<JobResponsibility>(jobResponsibility, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Retrieve failed!");
		}
	}
	
	@PostMapping(value = "/jobresponsibility")
	public ResponseEntity<?> insert(@RequestBody JobResponsibility jobResponsibility) throws Exception {
		try {
			jobRespService.insert(jobResponsibility);
			return ResponseEntity.ok("Insert success!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping(value = "/jobresponsibility")
	public ResponseEntity<?> update(@RequestBody JobResponsibility jobResponsibility) throws Exception {
		try {
			jobRespService.update(jobResponsibility);
			return ResponseEntity.ok("Update success with job responsibility ID: "+jobResponsibility.getId());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@DeleteMapping(value = "/jobresponsibility/{uuid}")
	public ResponseEntity<?> delete(@PathVariable String uuid) throws Exception {
		try {
			jobRespService.delete(uuid);
			return ResponseEntity.ok("Delete success with ID: "+uuid);
		} catch (Exception e) {
		    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Delete failed!");
		}
	}

}
