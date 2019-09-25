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
import com.demo.model.Module;
import com.demo.service.ModuleService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@RequestMapping({"/api/v1"})
public class ModuleController {
	
	@Autowired
	private ModuleService moduleService;

	@Transactional
	@GetMapping(value = "/modules")
    public ResponseEntity<?> getAllModules()
	{
		try{
				List<Module> listModule = moduleService.findAll();

				return ResponseEntity.ok(listModule);
		}
		catch(Exception e){
			 
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
	@Transactional
	@GetMapping(value = "/module/{id}")
    public ResponseEntity<?> getModule(@PathVariable String id)
	{
		try{
				Module module = moduleService.findById(id);

				return ResponseEntity.ok(module);
		}
		catch(Exception e){
			 
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
	@Transactional
	@PostMapping("/module")
    public ResponseEntity<?> postModule(@RequestBody Module module)
	{
		try{	
			moduleService.save(module);	
			return ResponseEntity.ok("Save Success");
		}
		catch(ValidationException val){
			 
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(val.getMessages());
		}
		catch (Exception e) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
	@Transactional
	@PutMapping("/module")
    public ResponseEntity<?> putModule(@RequestBody Module module)
	{
		try{	
			moduleService.update(module);	
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
	@DeleteMapping("/module/{id}")
    public ResponseEntity<?> deleteModule(@PathVariable String id)
	{
		try{	
			moduleService.delete(id);	
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
