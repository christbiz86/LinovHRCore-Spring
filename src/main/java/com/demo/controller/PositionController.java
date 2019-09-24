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

import com.demo.model.Position;
import com.demo.service.PositionService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@RequestMapping({ "/api/v1" })
public class PositionController {

	@Autowired
	private PositionService positionService;

	@GetMapping(value = "/positions")
	@Transactional
	public ResponseEntity<?> getPositions() {
		List<Position> positionList = positionService.findAll();
		return ResponseEntity.ok(positionList);
	}

	@GetMapping(value = "/position/{id}")
	@Transactional
	public ResponseEntity<?> getPositionById(@PathVariable String id) {
		Position position = positionService.findById(id);
		return ResponseEntity.ok(position);
	}

	@PostMapping(value = "/position")
	@Transactional
	public ResponseEntity<?> submit(@RequestBody Position position) throws Exception {
		try {
			positionService.save(position);
			return ResponseEntity.ok(HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insert Failed!");
		}
	}
	
	@PutMapping(value = "/position")
	@Transactional
	public ResponseEntity<?> update(@RequestBody Position position) throws Exception {
		try {
			positionService.update(position);
			return ResponseEntity.ok(HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Update Failed!");
		}
	}
	
	@DeleteMapping(value = "/position/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable String id) throws Exception {
		try {
			positionService.delete(id);
			return ResponseEntity.ok(HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Delete Failed!");
		}
	}
}
