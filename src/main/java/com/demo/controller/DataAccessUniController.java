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
import com.demo.model.DataAccessUni;
import com.demo.service.DataAccessUniService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@RequestMapping({ "/api/v1" })
public class DataAccessUniController {

	@Autowired
	private DataAccessUniService dataAccessUniService;

	@GetMapping(value = "/data-access-unis")
	@Transactional
	public ResponseEntity<?> getDataAccessUnis() {
		List<DataAccessUni> positionList = dataAccessUniService.findAll();
		return ResponseEntity.ok(positionList);
	}

	@GetMapping(value = "/data-access-uni/{id}")
	@Transactional
	public ResponseEntity<?> getDataAccessUniById(@PathVariable String id) {
		DataAccessUni dataAccessUni = dataAccessUniService.findById(id);
		return ResponseEntity.ok(dataAccessUni);
	}

	@PostMapping(value = "/data-access-uni")
	@Transactional
	public ResponseEntity<?> submit(@RequestBody DataAccessUni dataAccessUni) throws Exception {
		try {
			dataAccessUniService.save(dataAccessUni);
			return ResponseEntity.ok(HttpStatus.CREATED);
		} catch (ValidationException ex) {
			System.out.println(ex);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessages());
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} 
	}
	
	@PutMapping(value = "/data-access-uni")
	@Transactional
	public ResponseEntity<?> update(@RequestBody DataAccessUni dataAccessUni) throws Exception {
		try {
			dataAccessUniService.update(dataAccessUni);
			return ResponseEntity.ok(HttpStatus.OK);
		} catch (ValidationException ex) {
			System.out.println(ex);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessages());
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Update Failed!");
		}
	}
	
	@DeleteMapping(value = "/data-access-uni/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable String id) throws Exception {
		try {
			dataAccessUniService.delete(id);
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
