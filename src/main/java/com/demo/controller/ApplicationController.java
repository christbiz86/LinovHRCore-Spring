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

import com.demo.exception.ValidationException;
import com.demo.model.Application;
import com.demo.service.ApplicationService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@RequestMapping({"/api/v1"})
public class ApplicationController {
	
	@Autowired
	private ApplicationService applicationService;

	@Transactional
	@GetMapping(value = "/applications")
    public ResponseEntity<?> getAllApplication()
	{
		try{
				List<Application> listApplication = applicationService.findAll();

				return ResponseEntity.ok(listApplication);
		}
		catch(Exception e){
			 
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
	@Transactional
	@GetMapping(value = "/application/{id}")
    public ResponseEntity<?> getApplication(@PathVariable String id)
	{
		try{
				Application application = applicationService.findById(id);

				return ResponseEntity.ok(application);
		}
		catch(Exception e){
			 
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
	@Transactional
	@PostMapping("/application")
    public ResponseEntity<?> postApplication(@RequestBody Application application)
	{
		try{	
			applicationService.save(application);	
			return ResponseEntity.ok("Save Success");
		}
		catch(ValidationException val){
			 
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(val.getMessage());
		}
		catch (Exception e) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
	@Transactional
	@PutMapping("/application")
    public ResponseEntity<?> putApplication(@RequestBody Application application)
	{
		try{	
			applicationService.update(application);	
			return ResponseEntity.ok("Put Success");
		}
		catch(ValidationException val){
			 
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(val.getMessage());
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

    }
	
	@Transactional
	@DeleteMapping("/application/{id}")
    public ResponseEntity<?> deleteApplication(@PathVariable String id)
	{
		try{	
			applicationService.delete(id);	
			return ResponseEntity.ok("Delete Success");
		}
		catch(ValidationException val){
			 
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(val.getMessage());
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

    }
}
