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

import com.demo.model.LocationGroup;
import com.demo.service.LocationGroupService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@RequestMapping({"/api/v1"})
public class LocationGroupController {
	@Autowired
	private LocationGroupService locationGroupService;
	
	@GetMapping(value = "/location-groups")
	@Transactional
	public ResponseEntity<?> getAllLocationGroup() {
		try {
			List<LocationGroup> list = locationGroupService.findAll();
			return ResponseEntity.ok(list);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping(value = "/location-group/{id}")
	@Transactional
	public ResponseEntity<?> getLocationGroupById(@PathVariable String id) {
		try {
			LocationGroup locationGroup = locationGroupService.findById(id);
			return ResponseEntity.ok(locationGroup);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PostMapping(value = "/location-group")
	@Transactional
	public ResponseEntity<?> submit(@RequestBody LocationGroup locationGroup) throws Exception {
		try {
			locationGroupService.save(locationGroup);
			return ResponseEntity.ok("Data Have Successfully Saved");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping(value = "/location-group")
	@Transactional
	public ResponseEntity<?> update(@RequestBody LocationGroup locationGroup) throws Exception {
		try {
			locationGroupService.update(locationGroup);
			return ResponseEntity.ok("Data Have Successfull Updated");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@DeleteMapping(value = "/location-group/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable String id) throws Exception {
		try {
			locationGroupService.delete(id);
			return ResponseEntity.ok("Data Have Successfully Deleted");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
}
