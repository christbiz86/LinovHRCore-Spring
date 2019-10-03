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
import com.demo.model.DataAccess;
import com.demo.service.DataAccessService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@RequestMapping({ "/api/v1" })
public class DataAccessController {

	@Autowired
	private DataAccessService dataAccessService;

	@GetMapping(value = "/data-accesses")
	@Transactional
	public ResponseEntity<?> getDataAccesses() {
		List<DataAccess> positionList = dataAccessService.findAll();
		return ResponseEntity.ok(positionList);
	}

	@GetMapping(value = "/data-access/{id}")
	@Transactional
	public ResponseEntity<?> getDataAccessById(@PathVariable String id) {
		DataAccess dataAccess = dataAccessService.findById(id);
		return ResponseEntity.ok(dataAccess);
	}

	@PostMapping(value = "/data-access")
	@Transactional
	public ResponseEntity<?> submit(@RequestBody DataAccess dataAccess) throws Exception {
		try {
			dataAccessService.save(dataAccess);
			return ResponseEntity.ok(HttpStatus.CREATED);
		} catch (ValidationException ex) {
			System.out.println(ex);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessages());
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} 
	}
	
	@PutMapping(value = "/data-access")
	@Transactional
	public ResponseEntity<?> update(@RequestBody DataAccess dataAccess) throws Exception {
		try {
			dataAccessService.update(dataAccess);
			return ResponseEntity.ok(HttpStatus.OK);
		} catch (ValidationException ex) {
			System.out.println(ex);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessages());
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Update Failed!");
		}
	}
	
	@DeleteMapping(value = "/data-access/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable String id) throws Exception {
		try {
			dataAccessService.delete(id);
			return ResponseEntity.ok(HttpStatus.OK);
		} catch (ValidationException ex) {
			System.out.println(ex);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessages());
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Delete Failed!");
		}
	}
}
