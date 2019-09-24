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

import com.demo.model.JobResponsibility;
import com.demo.model.JobWorkingCondition;
import com.demo.service.JobWorkingConditionService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@RequestMapping(value = "/api/v1")
public class JobWorkingConditionController {
	
	@Autowired
	private JobWorkingConditionService jwcService;
	
	@GetMapping(value = "/lov/jobworkingconds")
	public ResponseEntity<?> findAll() throws Exception {
		try {
			List<JobWorkingCondition> list = jwcService.findAll();
			return new ResponseEntity<List<JobWorkingCondition>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Retrieve failed!");
		}
	}
	
	@GetMapping(value = "/jobworkingcond/{uuid}")
	public ResponseEntity<?> findById(@PathVariable String uuid) throws Exception {
		try {
			JobWorkingCondition jobWorkingCond = jwcService.findById(uuid);
			return new ResponseEntity<JobWorkingCondition>(jobWorkingCond, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Retrieve failed!");
		}
	}
	
	@PostMapping(value = "/jobworkingcond")
	public ResponseEntity<?> insert(@RequestBody JobWorkingCondition jobWorkingCond) throws Exception {
		try {
			jwcService.insert(jobWorkingCond);
			return ResponseEntity.ok("Insert success!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping(value = "/jobworkingcond")
	public ResponseEntity<?> update(@RequestBody JobWorkingCondition jobWorkingCond) throws Exception {
		try {
			jwcService.update(jobWorkingCond);
			return ResponseEntity.ok("Update success with job working condition ID: "+jobWorkingCond.getId());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@DeleteMapping(value = "/jobworkingcond/{uuid}")
	public ResponseEntity<?> delete(@PathVariable String uuid) throws Exception {
		try {
			jwcService.delete(uuid);
			return ResponseEntity.ok("Delete success with ID: "+uuid);
		} catch (Exception e) {
		    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Delete failed!");
		}
	}

}
