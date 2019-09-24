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
import com.demo.model.Menu;
import com.demo.service.MenuService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@RequestMapping({"/api/v1"})
public class MenuController {

	@Autowired
	private MenuService menuService;
	
	@Transactional
	@GetMapping(value = "/menus")
    public ResponseEntity<?> getAllMenu()
	{
		try{
				List<Menu> listMenu = menuService.findAll();

				return ResponseEntity.ok(listMenu);
		}
		catch(Exception e){
			 
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
	@Transactional
	@GetMapping(value = "/menu/{id}")
    public ResponseEntity<?> getMenu(@PathVariable String id)
	{
		try{
			Menu menu = menuService.findById(id);

			return ResponseEntity.ok(menu);
		}
		catch(Exception e){
			 
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
	@Transactional
	@PostMapping("/menu")
    public ResponseEntity<?> postMenu(@RequestBody Menu menu)
	{
		try{	
			menuService.save(menu);	
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
	@PutMapping("/menu")
    public ResponseEntity<?> putMenu(@RequestBody Menu menu)
	{
		try{	
			menuService.update(menu);	
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
	@DeleteMapping("/menu/{id}")
    public ResponseEntity<?> deleteMenu(@PathVariable String id)
	{
		try{	
			menuService.delete(id);	
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