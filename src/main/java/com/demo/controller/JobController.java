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

import com.demo.model.Job;
import com.demo.service.JobService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@RequestMapping(value = "/api/v1")
public class JobController {
	
	@Autowired
	private JobService jobService;
	
	@GetMapping(value = "/jobs")
	@Transactional
	public ResponseEntity<?> findAll() throws Exception {
		try {
			List<Job> list = jobService.findAll();
			return new ResponseEntity<List<Job>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Retrieve failed!");
		}
	}
	
	@GetMapping(value = "/job/{id}")
	@Transactional
	public ResponseEntity<?> findById(@PathVariable String id) throws Exception {
		try {
			Job job = jobService.findById(id);
			return new ResponseEntity<Job>(job, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Retrieve failed!");
		}
	}
	
	@GetMapping(value = "/job/code/{code}")
	@Transactional
	public ResponseEntity<?> findByCode(@PathVariable String code) throws Exception {
		try {
			Job job = jobService.findByCode(code);
			return new ResponseEntity<Job>(job, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Retrieve failed!");
		}
	}
	
	@PostMapping(value = "/job")
	@Transactional
	public ResponseEntity<?> insert(@RequestBody Job job) throws Exception {
		try {
			jobService.insert(job);
			return ResponseEntity.ok("Insert success with Job name: "+job.getName());
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping(value = "/job")
	@Transactional
	public ResponseEntity<?> update(@RequestBody Job job) throws Exception {
		try {
			jobService.update(job);
			return ResponseEntity.ok("Update success with Job ID: "+job.getId());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@DeleteMapping(value = "/job/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable String id) throws Exception {
		try {
			jobService.delete(id);
			return ResponseEntity.ok("Delete success with Job ID: "+id);
		} catch (Exception e) {
		    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Delete failed!");
		}
	}

}
