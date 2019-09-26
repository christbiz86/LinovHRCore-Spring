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

import com.demo.model.Assignment;
import com.demo.service.AssignmentService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@RequestMapping({"/api/v1"})
public class AssignmentController {

	@Autowired
	private AssignmentService assignmentService;

	@Transactional
	@GetMapping(value = "/assignments")
    public ResponseEntity<?> getAllAssignment()
	{
		try{
				List<Assignment> listAssignment = assignmentService.findAll();

				return ResponseEntity.ok(listAssignment);
		}
		catch(Exception e){
			 
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
	@Transactional
	@GetMapping(value = "/assignment/{id}")
    public ResponseEntity<?> getAssignment(@PathVariable String id)
	{
		try{
			Assignment assignment = assignmentService.findById(id);

			return ResponseEntity.ok(assignment);
		}
		catch(Exception e){
			 
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
	@Transactional
	@PostMapping("/assignment")
    public ResponseEntity<?> postAssignment(@RequestBody Assignment assignment)
	{
		try{	
			assignmentService.save(assignment);	
			return ResponseEntity.ok("Save Success");
		}
		catch (Exception e) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
	@Transactional
	@PutMapping("/assignment")
    public ResponseEntity<?> putAssignment(@RequestBody Assignment assignment)
	{
		try{	
			assignmentService.update(assignment);	
			return ResponseEntity.ok("Put Success");
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

    }
	
	@Transactional
	@DeleteMapping("/assignment/{id}")
    public ResponseEntity<?> deleteAssignment(@PathVariable String id)
	{
		try{	
			assignmentService.delete(id);	
			return ResponseEntity.ok("Delete Success");
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

    }
}
