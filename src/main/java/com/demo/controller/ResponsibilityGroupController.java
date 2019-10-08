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

import com.demo.model.ResponsibilityGroup;
import com.demo.service.ResponsibilityGroupService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@RequestMapping({"/api/v1"})
public class ResponsibilityGroupController {
	
	@Autowired
	private ResponsibilityGroupService responsibilityGroupService;

	@Transactional
	@GetMapping(value = "/responsibility/groups")
    public ResponseEntity<?> getAllResponsibilityGroup()
	{
		try{
				List<ResponsibilityGroup> listResponsibilityGroup = responsibilityGroupService.findAll();

				return ResponseEntity.ok(listResponsibilityGroup);
		}
		catch(Exception e){
			 
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
	@Transactional
	@GetMapping(value = "/responsibility/group/{id}")
    public ResponseEntity<?> getResponsibilityGroup(@PathVariable String id)
	{
		try{
			ResponsibilityGroup responsibilityGroup = responsibilityGroupService.findById(id);

			return ResponseEntity.ok(responsibilityGroup);
		}
		catch(Exception e){
			 
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
	@Transactional
	@PostMapping("/responsibility/group")
    public ResponseEntity<?> postResponsibilityGroup(@RequestBody ResponsibilityGroup responsibilityGroup)
	{
		try{	
			responsibilityGroupService.save(responsibilityGroup);	
			return ResponseEntity.ok("Save Success");
		}
		catch (Exception e) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
	@Transactional
	@PutMapping("/responsibility/group")
    public ResponseEntity<?> putResponsibilityGroup(@RequestBody ResponsibilityGroup responsibilityGroup)
	{
		try{	
			responsibilityGroupService.update(responsibilityGroup);	
			return ResponseEntity.ok("Put Success");
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

    }
	
	@Transactional
	@DeleteMapping("/responsibility/group/{id}")
    public ResponseEntity<?> deleteResponsibilityGroup(@PathVariable String id)
	{
		try{	
			responsibilityGroupService.delete(id);	
			return ResponseEntity.ok("Delete Success");
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

    }
}
