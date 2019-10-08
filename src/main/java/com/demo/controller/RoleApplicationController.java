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

import com.demo.model.RoleApplication;
import com.demo.service.RoleApplicationService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@RequestMapping({"/api/v1"})
public class RoleApplicationController {

	@Autowired
	private RoleApplicationService roleApplicationService;
	
	@Transactional
	@GetMapping(value = "/role/applications")
    public ResponseEntity<?> getAllRoleApplication()
	{
		try{
				List<RoleApplication> listRoleApplication = roleApplicationService.findAll();

				return ResponseEntity.ok(listRoleApplication);
		}
		catch(Exception e){
			 
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
	@Transactional
	@GetMapping(value = "/role/application/{id}")
    public ResponseEntity<?> getRoleApplication(@PathVariable String id)
	{
		try{
			RoleApplication roleApplication = roleApplicationService.findById(id);

			return ResponseEntity.ok(roleApplication);
		}
		catch(Exception e){
			 
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }

	@PostMapping(value = "/role/application")
	@Transactional
    public ResponseEntity<?> postRoleApplication(@RequestBody RoleApplication roleApplication)
	{
		try{	
			roleApplicationService.save(roleApplication);	
			return ResponseEntity.ok("Save Success");
		}
		catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

    }
	
	@Transactional
	@PutMapping(value = "/role/application")
    public ResponseEntity<?> putRoleApplication(@RequestBody RoleApplication roleApplication)
	{
		try{	
			roleApplicationService.update(roleApplication);	
			return ResponseEntity.ok("Put Success");
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

    }
	
	@Transactional
	@DeleteMapping("/role/application/{id}")
    public ResponseEntity<?> deleteRoleApplication(@PathVariable String id)
	{
		try{	
			roleApplicationService.delete(id);	
			return ResponseEntity.ok("Delete Success");
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

    }
}
