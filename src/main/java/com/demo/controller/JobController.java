package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.Job;
import com.demo.service.JobService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
public class JobController {
	
	@Autowired
	private JobService jobService;
	
	@GetMapping(value = "/lov/jobs")
	public ResponseEntity<?> findAll() throws Exception {
		try {
			List<Job> list = jobService.findAll();
			return new ResponseEntity<List<Job>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Retrieve failed!");
		}
	}
	
	@GetMapping(value = "/job/{id}")
	public ResponseEntity<?> findById(@PathVariable String id) throws Exception {
		try {
			Job job = jobService.findById(id);
			return new ResponseEntity<Job>(job, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Retrieve failed!");
		}
	}
	
	@GetMapping(value = "/job/code/{code}")
	public ResponseEntity<?> findByBk(@PathVariable String code) throws Exception {
		try {
			Job job = jobService.findByBk(code);
			return new ResponseEntity<Job>(job, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Retrieve failed!");
		}
	}

}
