package com.demo.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.UnitType;
import com.demo.service.UnitTypeService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@RequestMapping({"/api/v1"})
public class UnitTypeController {
	@Autowired
	private UnitTypeService unitTypeService;
	
	@GetMapping(value = "/unit-types")
	@Transactional
	public ResponseEntity<?> findAll(){
		try {
			List<UnitType> unitType = unitTypeService.findAll();
			return ResponseEntity.ok(unitType);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping(value = "/unit-type/{id}")
	@Transactional
	public ResponseEntity<?> getUnitTypeById(@PathVariable String id) {
		try {
			UnitType unitType = unitTypeService.findById(id);
			return ResponseEntity.ok(unitType);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PostMapping(value = "/unit-type")
	@Transactional
	public ResponseEntity<?> submit(@RequestBody UnitType unitType) throws Exception {
		try {
			unitTypeService.save(unitType);
			return ResponseEntity.ok("Data Have Successfully Saved");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping(value = "/unit-type")
	@Transactional
	public ResponseEntity<?> update(@RequestBody UnitType unitType) throws Exception {
		try {
			unitTypeService.update(unitType);
			return ResponseEntity.ok("Data Have Successfully Updated");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@DeleteMapping(value = "/unit-type/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable String id) throws Exception {
		try {
			unitTypeService.delete(id);
			return ResponseEntity.ok("Data Have Successfully Deleted");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
}
