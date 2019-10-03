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

import com.demo.model.Project;
import com.demo.service.ProjectService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@RequestMapping({"/api/v1"})
public class ProjectController {
	@Autowired
	private ProjectService projectService;
	
	@GetMapping(value = "/projects")
	@Transactional
	public ResponseEntity<?> getAllProject() {
		try {
			List<Project> list = projectService.findAll();
			return ResponseEntity.ok(list);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping(value = "/project/{id}")
	@Transactional
	public ResponseEntity<?> getProjectById(@PathVariable String id) {
		try {
			Project project = projectService.findById(id);
			return ResponseEntity.ok(project);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PostMapping(value = "/project")
	@Transactional
	public ResponseEntity<?> submit(@RequestBody Project project) throws Exception {
		try {
			projectService.save(project);
			return ResponseEntity.ok("Data Have Successfully Saved");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping(value = "/project")
	@Transactional
	public ResponseEntity<?> update(@RequestBody Project project) throws Exception {
		try {
			projectService.update(project);
			return ResponseEntity.ok("Data Have Successfully Updated");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@DeleteMapping(value = "/project/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable String id) throws Exception {
		try {
			projectService.delete(id);
			return ResponseEntity.ok("Data Have Successfully Deleted");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
}
