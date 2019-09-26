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

import com.demo.model.UserRoleWidget;
import com.demo.service.UserRoleWidgetService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@RequestMapping({"/api/v1"})
public class UserRoleWidgetController {

	@Autowired
	private UserRoleWidgetService userRoleWidgetService;
	
	@Transactional
	@GetMapping(value = "/userrole/widgets")
    public ResponseEntity<?> getAllUserRoleWidget()
	{
		try{
				List<UserRoleWidget> listUserRoleWidgets = userRoleWidgetService.findAll();

				return ResponseEntity.ok(listUserRoleWidgets);
		}
		catch(Exception e){
			 
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
	@Transactional
	@GetMapping(value = "/userrole/widget/{id}")
    public ResponseEntity<?> getUserRoleWidget(@PathVariable String id)
	{
		try{
				UserRoleWidget userRoleWidget = userRoleWidgetService.findById(id);

				return ResponseEntity.ok(userRoleWidget);
		}
		catch(Exception e){
			 
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
	@Transactional
	@PostMapping("/userrole/widget")
    public ResponseEntity<?> postUserRoleWidget(@RequestBody UserRoleWidget userRoleWidget)
	{
		try{	
			userRoleWidgetService.save(userRoleWidget);	
			return ResponseEntity.ok("Save Success");
		}
		catch (Exception e) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
	@Transactional
	@PutMapping("/userrole/widget")
    public ResponseEntity<?> putUserRoleWidget(@RequestBody UserRoleWidget userRoleWidget)
	{
		try{	
			userRoleWidgetService.update(userRoleWidget);	
			return ResponseEntity.ok("Put Success");
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

    }
	
	@Transactional
	@DeleteMapping("/userrole/widget/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id)
	{
		try{	
			userRoleWidgetService.delete(id);	
			return ResponseEntity.ok("Delete Success");
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

    }
}
