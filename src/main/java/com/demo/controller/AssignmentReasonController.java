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

import com.demo.model.AssignmentReason;
import com.demo.service.AssignmentReasonService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@RequestMapping(value = "/api/v1")
public class AssignmentReasonController {
	
	@Autowired
	private AssignmentReasonService arService;
	
	@GetMapping(value = "/assignment-reasons")
	@Transactional
	public ResponseEntity<?> findAll() throws Exception {
		try {
			List<AssignmentReason> list = arService.findAll();
			return new ResponseEntity<List<AssignmentReason>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Retrieve failed!");
		}
	}
	
	@GetMapping(value = "/assignment-reason/{id}")
	@Transactional
	public ResponseEntity<?> findById(@PathVariable String id) throws Exception {
		try {
			AssignmentReason ar = arService.findById(id);
			return new ResponseEntity<AssignmentReason>(ar, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Retrieve failed!");
		}
	}
	
	@GetMapping(value = "/assignment-reason/code/{code}")
	@Transactional
	public ResponseEntity<?> findByCode(@PathVariable String code) throws Exception {
		try {
			AssignmentReason ar = arService.findByCode(code);
			return new ResponseEntity<AssignmentReason>(ar, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Retrieve failed!");
		}
	}
	
	@PostMapping(value = "/assignment-reason")
	@Transactional
	public ResponseEntity<?> insert(@RequestBody AssignmentReason ar) throws Exception {
		try {
			arService.insert(ar);
			return ResponseEntity.ok("Insert success with Assignment Reason code: "+ar.getCode());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping(value = "/assignment-reason")
	@Transactional
	public ResponseEntity<?> update(@RequestBody AssignmentReason ar) throws Exception {
		try {
			arService.update(ar);
			return ResponseEntity.ok("Update success with Assignment Reason ID: "+ar.getId());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@DeleteMapping(value = "/assignment-reason/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable String id) throws Exception {
		try {
			arService.delete(id);
			return ResponseEntity.ok("Delete success with Assignment Reason ID: "+id);
		} catch (Exception e) {
		    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Delete failed!");
		}
	}

}
