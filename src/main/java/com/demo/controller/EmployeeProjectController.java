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

import com.demo.model.EmployeeProject;
import com.demo.service.EmployeeProjectService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@RequestMapping({"/api/v1"})
public class EmployeeProjectController {
	@Autowired
	private EmployeeProjectService employeeProjectService;
	
	@GetMapping(value = "/employee-projects")
	@Transactional
	public ResponseEntity<?> getAllEmpProject() {
		try {
			List<EmployeeProject> empPro = employeeProjectService.findAll();
			return ResponseEntity.ok(empPro);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping(value = "/employee-project/{id}")
	@Transactional
	public ResponseEntity<?> getEmpProjectById(@PathVariable String id) {
		try {
			EmployeeProject empPro = employeeProjectService.findById(id);
			return ResponseEntity.ok(empPro);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PostMapping(value = "/employee-project")
	@Transactional
	public ResponseEntity<?> submit(@RequestBody EmployeeProject employeeProject) throws Exception {
		try {
			employeeProjectService.save(employeeProject);
			return ResponseEntity.ok("Data Have Successfully Saved");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping(value = "/employee-project")
	@Transactional
	public ResponseEntity<?> update(@RequestBody EmployeeProject employeeProject) throws Exception {
		try {
			employeeProjectService.update(employeeProject);
			return ResponseEntity.ok("Data Have Successfully Updated");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@DeleteMapping(value = "/employee-project/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable String id) throws Exception {
		try {
			employeeProjectService.delete(id);
			return ResponseEntity.ok("Data Have Successfully Deleted");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
}
