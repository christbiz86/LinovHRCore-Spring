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

import com.demo.model.SettingLov;
import com.demo.service.SettingLovService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@RequestMapping({"/api/v1"})
public class SettingLovController {

	@Autowired
	private SettingLovService settingLovService;
	
	@Transactional
	@GetMapping(value = "/setting/lovs")
    public ResponseEntity<?> getAllSettingLov()
	{
		try{
				List<SettingLov> listSettingLov = settingLovService.findAll();

				return ResponseEntity.ok(listSettingLov);
		}
		catch(Exception e){
			 
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
	@Transactional
	@GetMapping(value = "/setting/lov/{id}")
    public ResponseEntity<?> getSettingLov(@PathVariable String id)
	{
		try{
			SettingLov settingLov = settingLovService.findById(id);

			return ResponseEntity.ok(settingLov);
		}
		catch(Exception e){
			 
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
	@Transactional
	@PostMapping("/setting/lov")
    public ResponseEntity<?> postSettingLov(@RequestBody SettingLov settingLov)
	{
		try{	
			settingLovService.save(settingLov);	
			return ResponseEntity.ok("Save Success");
		}
		catch (Exception e) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
	@Transactional
	@PutMapping("/setting/lov")
    public ResponseEntity<?> putRole(@RequestBody SettingLov settingLov)
	{
		try{	
			settingLovService.update(settingLov);	
			return ResponseEntity.ok("Put Success");
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

    }
	
	@Transactional
	@DeleteMapping("/setting/lov/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable String id)
	{
		try{	
			settingLovService.delete(id);	
			return ResponseEntity.ok("Delete Success");
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

    }
}
