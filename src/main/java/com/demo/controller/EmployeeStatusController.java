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

import com.demo.model.EmployeeStatus;
import com.demo.service.EmployeeStatusService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@RequestMapping({"/api/v1"})
public class EmployeeStatusController {

	@Autowired
	private EmployeeStatusService employeeStatusService;
	
	@Transactional
	@GetMapping(value = "/employee/statuses")
    public ResponseEntity<?> getAllEmployeeStatus()
	{
		try{
				List<EmployeeStatus> listEmployeeStatus = employeeStatusService.findAll();

				return ResponseEntity.ok(listEmployeeStatus);
		}
		catch(Exception e){
			 
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
	@Transactional
	@GetMapping(value = "/employee/status/{id}")
    public ResponseEntity<?> getEmployeeStatus(@PathVariable String id)
	{
		try{
			EmployeeStatus employeeStatus = employeeStatusService.findById(id);

			return ResponseEntity.ok(employeeStatus);
		}
		catch(Exception e){
			 
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
	@Transactional
	@PostMapping("/employee/status")
    public ResponseEntity<?> postEmployeeStatus(@RequestBody EmployeeStatus employeeStatus)
	{
		try{	
			employeeStatusService.save(employeeStatus);	
			return ResponseEntity.ok("Save Success");
		}
		catch (Exception e) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
	@Transactional
	@PutMapping("/employee/status")
    public ResponseEntity<?> putEmployeeStatus(@RequestBody EmployeeStatus employeeStatus)
	{
		try{	
			employeeStatusService.update(employeeStatus);	
			return ResponseEntity.ok("Put Success");
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

    }
	
	@Transactional
	@DeleteMapping("/employee/status/{id}")
    public ResponseEntity<?> deleteEmployeeStatus(@PathVariable String id)
	{
		try{	
			employeeStatusService.delete(id);	
			return ResponseEntity.ok("Delete Success");
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

    }
}
