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

import com.demo.model.RoleMenu;
import com.demo.service.RoleMenuService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@RequestMapping({"/api/v1"})
public class RoleMenuController {
	
	@Autowired
	private RoleMenuService roleMenuService;

	@Transactional
	@GetMapping(value = "/role/menus")
    public ResponseEntity<?> getAllRoleMenu()
	{
		try{
				List<RoleMenu> listRoleMenu = roleMenuService.findAll();

				return ResponseEntity.ok(listRoleMenu);
		}
		catch(Exception e){
			 
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
	@Transactional
	@GetMapping(value = "/role/menu/{id}")
    public ResponseEntity<?> getRoleMenu(@PathVariable String id)
	{
		try{
				RoleMenu roleMenu = roleMenuService.findById(id);

				return ResponseEntity.ok(roleMenu);
		}
		catch(Exception e){
			 
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
	@Transactional
	@PostMapping("/role/menu")
    public ResponseEntity<?> postRoleMenu(@RequestBody RoleMenu roleMenu)
	{
		try{	
			roleMenuService.save(roleMenu);	
			return ResponseEntity.ok("Save Success");
		}
		catch (Exception e) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
	@Transactional
	@PutMapping("/role/menu")
    public ResponseEntity<?> putRoleMenu(@RequestBody RoleMenu roleMenu)
	{
		try{	
			roleMenuService.update(roleMenu);	
			return ResponseEntity.ok("Put Success");
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

    }
	
	@Transactional
	@DeleteMapping("/role/menu/{id}")
    public ResponseEntity<?> deleteRoleMenu(@PathVariable String id)
	{
		try{	
			roleMenuService.delete(id);	
			return ResponseEntity.ok("Delete Success");
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

    }
}
