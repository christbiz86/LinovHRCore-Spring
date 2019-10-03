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
import com.demo.model.PositionSlot;
import com.demo.service.PositionSlotService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@RequestMapping({ "/api/v1" })
public class PositionSlotController {

	@Autowired
	private PositionSlotService positionSlotService;

	@GetMapping(value = "/position-slots")
	@Transactional
	public ResponseEntity<?> getPositionSlots() {
		List<PositionSlot> positionList = positionSlotService.findAll();
		return ResponseEntity.ok(positionList);
	}

	@GetMapping(value = "/position-slot/{id}")
	@Transactional
	public ResponseEntity<?> getPositionSlotById(@PathVariable String id) {
		PositionSlot position = positionSlotService.findById(id);
		return ResponseEntity.ok(position);
	}

	@PostMapping(value = "/position-slot")
	@Transactional
	public ResponseEntity<?> submit(@RequestBody PositionSlot position) throws Exception {
		try {
			positionSlotService.save(position);
			return ResponseEntity.ok(HttpStatus.CREATED);
		} catch (ValidationException ex) {
			System.out.println(ex);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessages());
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} 
	}
	
	@PutMapping(value = "/position-slot")
	@Transactional
	public ResponseEntity<?> update(@RequestBody PositionSlot position) throws Exception {
		try {
			positionSlotService.update(position);
			return ResponseEntity.ok(HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Update Failed!");
		}
	}
	
	@DeleteMapping(value = "/position-slot/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable String id) throws Exception {
		try {
			positionSlotService.delete(id);
			return ResponseEntity.ok(HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Delete Failed!");
		}
	}
}
