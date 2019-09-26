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

import com.demo.model.LocationGroupDetail;
import com.demo.service.LocationGroupDetailService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@RequestMapping({"/api/v1"})
public class LocationGroupDetailController {
	@Autowired
	private LocationGroupDetailService locationGroupDetailService;
	
	@GetMapping(value = "/location-group-details")
	@Transactional
	public ResponseEntity<?> findAll() {
		try {
			List<LocationGroupDetail> list = locationGroupDetailService.findAll();
			return ResponseEntity.ok(list);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping(value = "/location-group-detail/{id}")
	@Transactional
	public ResponseEntity<?> getById(@PathVariable String id) {
		try {
			LocationGroupDetail locationDetail = locationGroupDetailService.findById(id);
			return ResponseEntity.ok(locationDetail);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PostMapping(value = "/location-group-detail")
	@Transactional
	public ResponseEntity<?> submit(@RequestBody LocationGroupDetail locationGroupDetail) throws Exception {
		try {
			locationGroupDetailService.save(locationGroupDetail);
			return ResponseEntity.ok("Data Have Successfully Saved");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping(value = "/location-group-detail")
	@Transactional
	public ResponseEntity<?> update(@RequestBody LocationGroupDetail locationGroupDetail) throws Exception {
		try {
			locationGroupDetailService.update(locationGroupDetail);
			return ResponseEntity.ok("Data Have Successfully Updated");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@DeleteMapping(value = "/location-group-detail/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable String id) throws Exception {
		try {
			locationGroupDetailService.delete(id);
			return ResponseEntity.ok("Data Have Successfully Deleted");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
}
