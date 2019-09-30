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

import com.demo.model.MenuAction;
import com.demo.service.MenuActionService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@RequestMapping({"/api/v1"})
public class MenuActionController {

	@Autowired
	private MenuActionService menuActionService;
	
	@Transactional
	@GetMapping(value = "/menu-actions")
    public ResponseEntity<?> getAllMenuAction()
	{
		try{
				List<MenuAction> listMenuAction = menuActionService.findAll();

				return ResponseEntity.ok(listMenuAction);
		}
		catch(Exception e){
			 
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
	@Transactional
	@GetMapping(value = "/menu-action/{id}")
    public ResponseEntity<?> getMenuAction(@PathVariable String id)
	{
		try{
				MenuAction menuAction = menuActionService.findById(id);

				return ResponseEntity.ok(menuAction);
		}
		catch(Exception e){
			 
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
	@Transactional
	@PostMapping("/menu-action")
    public ResponseEntity<?> postMenuAction(@RequestBody MenuAction menuAction)
	{
		try{	
			menuActionService.save(menuAction);	
			return ResponseEntity.ok("Save Success");
		}
		catch (Exception e) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
	@Transactional
	@PutMapping("/menu-action")
    public ResponseEntity<?> putMenuAction(@RequestBody MenuAction menuAction)
	{
		try{	
			menuActionService.update(menuAction);	
			return ResponseEntity.ok("Put Success");
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

    }
	
	@Transactional
	@DeleteMapping("/menu-action/{id}")
    public ResponseEntity<?> deleteMenuAction(@PathVariable String id)
	{
		try{	
			menuActionService.delete(id);	
			return ResponseEntity.ok("Delete Success");
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

    }
}
