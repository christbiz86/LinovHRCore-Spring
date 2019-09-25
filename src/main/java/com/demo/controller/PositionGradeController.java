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
import com.demo.model.PositionGrade;
import com.demo.service.PositionGradeService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@RequestMapping({ "/api/v1" })
public class PositionGradeController {

	@Autowired
	private PositionGradeService positionGradeService;

	@GetMapping(value = "/position-grades")
	@Transactional
	public ResponseEntity<?> getPositionGrades() {
		List<PositionGrade> positionList = positionGradeService.findAll();
		return ResponseEntity.ok(positionList);
	}

	@GetMapping(value = "/position-grade/{id}")
	@Transactional
	public ResponseEntity<?> getPositionGradeById(@PathVariable String id) {
		PositionGrade position = positionGradeService.findById(id);
		return ResponseEntity.ok(position);
	}

	@PostMapping(value = "/position-grade")
	@Transactional
	public ResponseEntity<?> submit(@RequestBody PositionGrade position) throws Exception {
		try {
			positionGradeService.save(position);
			return ResponseEntity.ok(HttpStatus.CREATED);
		} catch (ValidationException ex) {
			System.out.println(ex);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessages());
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} 
	}
	
	@PutMapping(value = "/position-grade")
	@Transactional
	public ResponseEntity<?> update(@RequestBody PositionGrade position) throws Exception {
		try {
			positionGradeService.update(position);
			return ResponseEntity.ok(HttpStatus.OK);
		} catch (ValidationException ex) {
			System.out.println(ex);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessages());
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Update Failed!");
		}
	}
	
	@DeleteMapping(value = "/position-grade/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable String id) throws Exception {
		try {
			positionGradeService.delete(id);
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
