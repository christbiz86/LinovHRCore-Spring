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

import com.demo.model.CompanyLocation;
import com.demo.service.CompanyLocationService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@RequestMapping({"/api/v1"})
public class CompanyLocationController {
	@Autowired
	private CompanyLocationService companyLocationService;
	
	@GetMapping(value = "/company-locations")
	@Transactional
	public ResponseEntity<?> getAll() {
		try {
			List<CompanyLocation> companyLocation = companyLocationService.findAll();
			return ResponseEntity.ok(companyLocation);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping(value = "/company-location/{id}")
	@Transactional
	public ResponseEntity<?> getById(@PathVariable String id) {
		try {
			CompanyLocation companyLocation = companyLocationService.findById(id);
			return ResponseEntity.ok(companyLocation);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PostMapping(value = "/company-location")
	@Transactional
	public ResponseEntity<?> submit(@RequestBody CompanyLocation compLocation) throws Exception {
		try {
			companyLocationService.save(compLocation);
			return ResponseEntity.ok("Data Have Successfully Saved");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping(value = "/company-location")
	@Transactional
	public ResponseEntity<?> update(@RequestBody CompanyLocation compLocation) throws Exception {
		try {
			companyLocationService.update(compLocation);
			return ResponseEntity.ok("Data Have Successfully Updated");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@DeleteMapping(value = "/company-location/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable String id) throws Exception {
		try {
			companyLocationService.delete(id);
			return ResponseEntity.ok("Data Have Successfully Deleted");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
}
