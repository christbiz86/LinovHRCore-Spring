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

import com.demo.model.UserSetting;
import com.demo.service.UserSettingService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@RequestMapping({"/api/v1"})
public class UserSettingController {

	@Autowired
	private UserSettingService userSettingService;
	
	@Transactional
	@GetMapping(value = "/user-settings")
    public ResponseEntity<?> getAllUserSetting()
	{
		try{
				List<UserSetting> listUserSetting = userSettingService.findAll();

				return ResponseEntity.ok(listUserSetting);
		}
		catch(Exception e){
			 
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
	@Transactional
	@GetMapping(value = "/user-setting/{id}")
    public ResponseEntity<?> getUserSetting(@PathVariable String id)
	{
		try{
			UserSetting userSetting = userSettingService.findById(id);

			return ResponseEntity.ok(userSetting);
		}
		catch(Exception e){
			 
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
	@Transactional
	@PostMapping("/user-setting")
    public ResponseEntity<?> postUserSetting(@RequestBody UserSetting userSetting)
	{
		try{	
			userSettingService.save(userSetting);	
			return ResponseEntity.ok("Save Success");
		}
		catch (Exception e) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
	@Transactional
	@PutMapping("/user-setting")
    public ResponseEntity<?> putUserSetting(@RequestBody UserSetting userSetting)
	{
		try{	
			userSettingService.update(userSetting);	
			return ResponseEntity.ok("Put Success");
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

    }
	
	@Transactional
	@DeleteMapping("/user-setting/{id}")
    public ResponseEntity<?> deleteUserSetting(@PathVariable String id)
	{
		try{	
			userSettingService.delete(id);	
			return ResponseEntity.ok("Delete Success");
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

    }
}
