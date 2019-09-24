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
import com.demo.model.Responsibility;
import com.demo.service.ResponsibilityService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@RequestMapping({"/api/v1"})
public class ResponsibilityController {

	@Autowired
	private ResponsibilityService responsibilityService;
	
	@Transactional
	@GetMapping(value = "/responsibilities")
    public ResponseEntity<?> getAllResponsibility()
	{
		try{
				List<Responsibility> listResponsibility = responsibilityService.findAll();

				return ResponseEntity.ok(listResponsibility);
		}
		catch(Exception e){
			 
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
	@Transactional
	@GetMapping(value = "/responsibility/{id}")
    public ResponseEntity<?> getResponsibility(@PathVariable String id)
	{
		try{
			Responsibility responsibility = responsibilityService.findById(id);

			return ResponseEntity.ok(responsibility);
		}
		catch(Exception e){
			 
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
	@Transactional
	@PostMapping("/responsibility")
    public ResponseEntity<?> postResponsibility(@RequestBody Responsibility responsibility)
	{
		try{	
			responsibilityService.save(responsibility);	
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
	@PutMapping("/responsibility")
    public ResponseEntity<?> putResponsibility(@RequestBody Responsibility responsibility)
	{
		try{	
			responsibilityService.update(responsibility);	
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
	@DeleteMapping("/responsibility/{id}")
    public ResponseEntity<?> deleteResponsibility(@PathVariable String id)
	{
		try{	
			responsibilityService.delete(id);	
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
