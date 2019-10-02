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

import com.demo.model.TenantModule;
import com.demo.service.TenantModuleService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@RequestMapping(value = "/api/v1")
public class TenantModuleController {
	
	@Autowired
	private TenantModuleService tmService;
	
	@GetMapping(value = "/tenant-modules")
	@Transactional
	public ResponseEntity<?> findAll() throws Exception {
		try {
			List<TenantModule> list = tmService.findAll();
			return new ResponseEntity<List<TenantModule>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Retrieve failed!");
		}
	}
	
	@GetMapping(value = "/tenant-module/{id}")
	@Transactional
	public ResponseEntity<?> findById(@PathVariable String id) throws Exception {
		try {
			TenantModule tm = tmService.findById(id);
			return new ResponseEntity<TenantModule>(tm, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Retrieve failed!");
		}
	}
	
	@PostMapping(value = "/tenant-module")
	@Transactional
	public ResponseEntity<?> insert(@RequestBody TenantModule tm) throws Exception {
		try {
			tmService.insert(tm);
			return ResponseEntity.ok("Insert success!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping(value = "/tenant-module")
	@Transactional
	public ResponseEntity<?> update(@RequestBody TenantModule tm) throws Exception {
		try {
			tmService.update(tm);
			return ResponseEntity.ok("Update success with Tenant Module ID: "+tm.getId());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@DeleteMapping(value = "/tenant-module/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable String id) throws Exception {
		try {
			tmService.delete(id);
			return ResponseEntity.ok("Delete success with Tenant Module ID: "+id);
		} catch (Exception e) {
		    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Delete failed!");
		}
	}

}
