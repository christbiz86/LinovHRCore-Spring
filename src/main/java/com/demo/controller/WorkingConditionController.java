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

import com.demo.model.WorkingCondition;
import com.demo.service.WorkingConditionService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@RequestMapping(value = "/api/v1")
public class WorkingConditionController {
	
	@Autowired
	private WorkingConditionService wcService;
	
	@GetMapping(value = "/lov/working-conditions")
	@Transactional
	public ResponseEntity<?> findAll() throws Exception {
		try {
			List<WorkingCondition> list = wcService.findAll();
			return new ResponseEntity<List<WorkingCondition>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Retrieve failed!");
		}
	}
	
	@GetMapping(value = "/working-condition/{id}")
	@Transactional
	public ResponseEntity<?> findById(@PathVariable String id) throws Exception {
		try {
			WorkingCondition wc = wcService.findById(id);
			return new ResponseEntity<WorkingCondition>(wc, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Retrieve failed!");
		}
	}
	
	@GetMapping(value = "/working-condition/code/{code}")
	@Transactional
	public ResponseEntity<?> findByCode(@PathVariable String code) throws Exception {
		try {
			WorkingCondition wc = wcService.findByCode(code);
			return new ResponseEntity<WorkingCondition>(wc, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Retrieve failed!");
		}
	}
	
	@PostMapping(value = "/working-condition")
	@Transactional
	public ResponseEntity<?> insert(@RequestBody WorkingCondition wc) throws Exception {
		try {
			wcService.insert(wc);
			return ResponseEntity.ok("Insert success with Working Condition name: "+wc.getName());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping(value = "/working-condition")
	@Transactional
	public ResponseEntity<?> update(@RequestBody WorkingCondition wc) throws Exception {
		try {
			wcService.update(wc);
			return ResponseEntity.ok("Update success with Working Condition ID: "+wc.getId());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@DeleteMapping(value = "/working-condition/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable String id) throws Exception {
		try {
			wcService.delete(id);
			return ResponseEntity.ok("Delete success with Working Condition ID: "+id);
		} catch (Exception e) {
		    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Delete failed!");
		}
	}

}
