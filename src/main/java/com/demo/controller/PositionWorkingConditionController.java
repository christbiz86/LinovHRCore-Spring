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
import com.demo.model.PositionWorkingCondition;
import com.demo.service.PositionWorkingConditionService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@RequestMapping({ "/api/v1" })
public class PositionWorkingConditionController {

	@Autowired
	private PositionWorkingConditionService posWCSvc;
	
	@GetMapping(value = "/position-working-conditions")
	@Transactional
	public ResponseEntity<?> getPositionSlots() {
		List<PositionWorkingCondition> positionList = posWCSvc.findAll();
		return ResponseEntity.ok(positionList);
	}

	@GetMapping(value = "/position-working-condition/{id}")
	@Transactional
	public ResponseEntity<?> getPositionSlotById(@PathVariable String id) {
		PositionWorkingCondition position = posWCSvc.findById(id);
		return ResponseEntity.ok(position);
	}

	@PostMapping(value = "/position-working-condition")
	@Transactional
	public ResponseEntity<?> submit(@RequestBody PositionWorkingCondition position) throws Exception {
		try {
			posWCSvc.save(position);
			return ResponseEntity.ok(HttpStatus.CREATED);
		} catch (ValidationException ex) {
			System.out.println(ex);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessages());
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insert Failed!");
		}
	}
	
	@PutMapping(value = "/position-working-condition")
	@Transactional
	public ResponseEntity<?> update(@RequestBody PositionWorkingCondition position) throws Exception {
		try {
			posWCSvc.update(position);
			return ResponseEntity.ok(HttpStatus.OK);
		} catch (ValidationException ex) {
			System.out.println(ex);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessages());
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Update Failed!");
		}
	}
	
	@DeleteMapping(value = "/position-working-condition/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable String id) throws Exception {
		try {
			posWCSvc.delete(id);
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
