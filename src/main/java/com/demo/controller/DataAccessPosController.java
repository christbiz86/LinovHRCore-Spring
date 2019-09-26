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
import com.demo.model.DataAccessPos;
import com.demo.service.DataAccessPosService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@RequestMapping({ "/api/v1" })
public class DataAccessPosController {

	@Autowired
	private DataAccessPosService dataAccessPosService;

	@GetMapping(value = "/data-access-poss")
	@Transactional
	public ResponseEntity<?> getDataAccessUnis() {
		List<DataAccessPos> positionList = dataAccessPosService.findAll();
		return ResponseEntity.ok(positionList);
	}

	@GetMapping(value = "/data-access-pos/{id}")
	@Transactional
	public ResponseEntity<?> getDataAccessUniById(@PathVariable String id) {
		DataAccessPos dataAccessPos = dataAccessPosService.findById(id);
		return ResponseEntity.ok(dataAccessPos);
	}

	@PostMapping(value = "/data-access-pos")
	@Transactional
	public ResponseEntity<?> submit(@RequestBody DataAccessPos dataAccessPos) throws Exception {
		try {
			dataAccessPosService.save(dataAccessPos);
			return ResponseEntity.ok(HttpStatus.CREATED);
		} catch (ValidationException ex) {
			System.out.println(ex);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessages());
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} 
	}
	
	@PutMapping(value = "/data-access-pos")
	@Transactional
	public ResponseEntity<?> update(@RequestBody DataAccessPos dataAccessPos) throws Exception {
		try {
			dataAccessPosService.update(dataAccessPos);
			return ResponseEntity.ok(HttpStatus.OK);
		} catch (ValidationException ex) {
			System.out.println(ex);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessages());
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Update Failed!");
		}
	}
	
	@DeleteMapping(value = "/data-access-pos/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable String id) throws Exception {
		try {
			dataAccessPosService.delete(id);
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
