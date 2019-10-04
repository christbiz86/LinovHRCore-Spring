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

import com.demo.model.Employee;
import com.demo.service.EmployeeService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@RequestMapping({"/api/v1"})
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;

	@Transactional
	@GetMapping(value = "/employees")
    public ResponseEntity<?> getAllEmployee()
	{
		try{
				List<Employee> listEmployee = employeeService.findAll();

				return ResponseEntity.ok(listEmployee);
		}
		catch(Exception e){
			 
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
	@Transactional
	@GetMapping(value = "/employee/{id}")
    public ResponseEntity<?> getEmployee(@PathVariable String id)
	{
		try{
			Employee employee = employeeService.findById(id);

			return ResponseEntity.ok(employee);
		}
		catch(Exception e){
			 
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
	@Transactional
	@GetMapping(value = "/employee/person/{personId}/assignment/{assignmentId}")
    public ResponseEntity<?> getEmployeeByPersonAndAssignment(@PathVariable String personId,@PathVariable String assignmentId)
	{
		try{
			Employee employee = employeeService.findByBk(personId, assignmentId);

			return ResponseEntity.ok(employee);
		}
		catch(Exception e){
			 
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
	@Transactional
	@GetMapping(value = "/employee/person/{personId}")
    public ResponseEntity<?> getEmployeeByPerson(@PathVariable String personId)
	{
		try{
			List<Employee> listEmployee = employeeService.findByPerson(personId);

			return ResponseEntity.ok(listEmployee);
		}
		catch(Exception e){
			 
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
	@Transactional
	@PostMapping("/employee")
    public ResponseEntity<?> postEmployee(@RequestBody Employee employee)
	{
		try{	
			employeeService.save(employee);	
			return ResponseEntity.ok("Save Success");
		}
		catch (Exception e) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
	@Transactional
	@PutMapping("/employee")
    public ResponseEntity<?> putEmployee(@RequestBody Employee employee)
	{
		try{	
			employeeService.update(employee);	
			return ResponseEntity.ok("Put Success");
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

    }
	
	@Transactional
	@DeleteMapping("/employee/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable String id)
	{
		try{	
			employeeService.delete(id);	
			return ResponseEntity.ok("Delete Success");
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

    }
}
