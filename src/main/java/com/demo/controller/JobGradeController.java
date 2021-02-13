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

import com.demo.model.JobGrade;
import com.demo.service.JobGradeService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@RequestMapping(value = "/api/v1")
public class JobGradeController {
	
	@Autowired
	private JobGradeService jobGradeService;
	
	@GetMapping(value = "/job-grades")
	@Transactional
	public ResponseEntity<?> findAll() throws Exception {
		try {
			List<JobGrade> list = jobGradeService.findAll();
			return new ResponseEntity<List<JobGrade>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Retrieve failed!");
		}
	}
	
	@GetMapping(value = "/job-grade/{id}")
	@Transactional
	public ResponseEntity<?> findById(@PathVariable String id) throws Exception {
		try {
			JobGrade jobGrade = jobGradeService.findById(id);
			return new ResponseEntity<JobGrade>(jobGrade, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Retrieve failed!");
		}
	}
	
	@PostMapping(value = "/job-grade")
	@Transactional
	public ResponseEntity<?> insert(@RequestBody JobGrade jobGrade) throws Exception {
		try {
			jobGradeService.insert(jobGrade);
			return ResponseEntity.ok("Insert success!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping(value = "/job-grade")
	@Transactional
	public ResponseEntity<?> update(@RequestBody JobGrade jobGrade) throws Exception {
		try {
			jobGradeService.update(jobGrade);
			return ResponseEntity.ok("Update success with job grade ID: "+jobGrade.getId());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@DeleteMapping(value = "/job-grade/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable String id) throws Exception {
		try {
			jobGradeService.delete(id);
			return ResponseEntity.ok("Delete success with ID: "+id);
		} catch (Exception e) {
		    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Delete failed!");
		}
	}

}
