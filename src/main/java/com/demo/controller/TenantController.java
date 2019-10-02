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

import com.demo.model.Tenant;
import com.demo.service.TenantService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@RequestMapping(value = "/api/v1")
public class TenantController {
	
	@Autowired
	private TenantService tenantService;
	
	@GetMapping(value = "/tenants")
	@Transactional
	public ResponseEntity<?> findAll() throws Exception {
		try {
			List<Tenant> list = tenantService.findAll();
			return new ResponseEntity<List<Tenant>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Retrieve failed!");
		}
	}
	
	@GetMapping(value = "/tenant/{id}")
	@Transactional
	public ResponseEntity<?> findById(@PathVariable String id) throws Exception {
		try {
			Tenant tenant = tenantService.findById(id);
			return new ResponseEntity<Tenant>(tenant, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Retrieve failed!");
		}
	}
	
	@GetMapping(value = "/tenant/code/{code}")
	@Transactional
	public ResponseEntity<?> findByCode(@PathVariable String code) throws Exception {
		try {
			Tenant tenant = tenantService.findByCode(code);
			return new ResponseEntity<Tenant>(tenant, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Retrieve failed!");
		}
	}
	
	@PostMapping(value = "/tenant")
	@Transactional
	public ResponseEntity<?> insert(@RequestBody Tenant tenant) throws Exception {
		try {
			tenantService.insert(tenant);
			return ResponseEntity.ok("Insert success with Tenant name: "+tenant.getName());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping(value = "/tenant")
	@Transactional
	public ResponseEntity<?> update(@RequestBody Tenant tenant) throws Exception {
		try {
			tenantService.update(tenant);
			return ResponseEntity.ok("Update success with Tenant ID: "+tenant.getId());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@DeleteMapping(value = "/tenant/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable String id) throws Exception {
		try {
			tenantService.delete(id);
			return ResponseEntity.ok("Delete success with Tenant ID: "+id);
		} catch (Exception e) {
		    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Delete failed!");
		}
	}

}
