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

import com.demo.model.WorkingConditionType;
import com.demo.service.WorkingConditionTypeService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@RequestMapping(value = "/api/v1")
public class WorkingConditionTypeController {
	
	@Autowired
	private WorkingConditionTypeService wctService;
	
	@GetMapping(value = "/lov/working-condition-types")
	@Transactional
	public ResponseEntity<?> findAll() throws Exception {
		try {
			List<WorkingConditionType> list = wctService.findAll();
			return new ResponseEntity<List<WorkingConditionType>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Retrieve failed!");
		}
	}
	
	@GetMapping(value = "/working-condition-type/{id}")
	@Transactional
	public ResponseEntity<?> findById(@PathVariable String id) throws Exception {
		try {
			WorkingConditionType wct = wctService.findById(id);
			return new ResponseEntity<WorkingConditionType>(wct, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Retrieve failed!");
		}
	}
	
	@GetMapping(value = "/working-condition-type/code/{code}")
	@Transactional
	public ResponseEntity<?> findByCode(@PathVariable String code) throws Exception {
		try {
			WorkingConditionType wct = wctService.findByCode(code);
			return new ResponseEntity<WorkingConditionType>(wct, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Retrieve failed!");
		}
	}
	
	@PostMapping(value = "/working-condition-type")
	@Transactional
	public ResponseEntity<?> insert(@RequestBody WorkingConditionType wct) throws Exception {
		try {
			wctService.insert(wct);
			return ResponseEntity.ok("Insert success with Working Condition name: "+wct.getName());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping(value = "/working-condition-type")
	@Transactional
	public ResponseEntity<?> update(@RequestBody WorkingConditionType wct) throws Exception {
		try {
			wctService.update(wct);
			return ResponseEntity.ok("Update success with Working Condition ID: "+wct.getId());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@DeleteMapping(value = "/working-condition-type/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable String id) throws Exception {
		try {
			wctService.delete(id);
			return ResponseEntity.ok("Delete success with ID: "+id);
		} catch (Exception e) {
		    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Delete failed!");
		}
	}

}
