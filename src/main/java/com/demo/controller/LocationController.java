package com.demo.controller;

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

import com.demo.exception.ValidationException;
import com.demo.model.Location;
import com.demo.service.LocationService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@RequestMapping({"/api/v1"})
public class LocationController {
	@Autowired
	private LocationService locationService;
	
	@GetMapping(value = "/location/{id}")
	@Transactional
	public ResponseEntity<?> getLocationById(@PathVariable String id) {
		try {
			Location location = locationService.findById(id);
			return ResponseEntity.ok(location);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		
	}
	
	@PostMapping(value = "/location")
	@Transactional
	public ResponseEntity<?> submit(@RequestBody Location location) throws Exception {
		try {
			locationService.save(location);
			return ResponseEntity.ok("Data Have Successfully Saved");
		} catch (ValidationException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessages());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping(value = "/location")
	@Transactional
	public ResponseEntity<?> update(@RequestBody Location location) throws Exception {
		try {
			locationService.update(location);
			return ResponseEntity.ok("Data Have Successfully Updated");
		} catch (ValidationException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessages());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@DeleteMapping(value = "/location/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable String id) throws Exception {
		try {
			locationService.delete(id);
			return ResponseEntity.ok("Data Have Successfully Deleted");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
}
