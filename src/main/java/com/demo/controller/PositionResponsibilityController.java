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
import com.demo.model.PositionResponsibility;
import com.demo.service.PositionResponsibilityService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@RequestMapping({ "/api/v1" })
public class PositionResponsibilityController {

	@Autowired
	private PositionResponsibilityService posRespService;
	
	@GetMapping(value = "/position-responsibilities")
	@Transactional
	public ResponseEntity<?> getPositionResponsibilities() {
		List<PositionResponsibility> positionList = posRespService.findAll();
		return ResponseEntity.ok(positionList);
	}

	@GetMapping(value = "/position-responsibility/{id}")
	@Transactional
	public ResponseEntity<?> getPositionResponsibilityById(@PathVariable String id) {
		PositionResponsibility position = posRespService.findById(id);
		return ResponseEntity.ok(position);
	}

	@PostMapping(value = "/position-responsibility")
	@Transactional
	public ResponseEntity<?> submit(@RequestBody PositionResponsibility position) throws Exception {
		try {
			posRespService.save(position);
			return ResponseEntity.ok(HttpStatus.CREATED);
		} catch (ValidationException ex) {
			System.out.println(ex);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessages());
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insert Failed!");
		}
	}
	
	@PutMapping(value = "/position-responsibility")
	@Transactional
	public ResponseEntity<?> update(@RequestBody PositionResponsibility position) throws Exception {
		try {
			posRespService.update(position);
			return ResponseEntity.ok(HttpStatus.OK);
		} catch (ValidationException ex) {
			System.out.println(ex);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessages());
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Update Failed!");
		}
	}
	
	@DeleteMapping(value = "/position-responsibility/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable String id) throws Exception {
		try {
			posRespService.delete(id);
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
