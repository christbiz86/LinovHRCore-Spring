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

import com.demo.model.SettingType;
import com.demo.service.SettingTypeService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@RequestMapping({"/api/v1"})
public class SettingTypeController {
	
	@Autowired
	private SettingTypeService settingTypeService;

	@Transactional
	@GetMapping(value = "/setting/types")
    public ResponseEntity<?> getAllSettingType()
	{
		try{
				List<SettingType> listSettingType = settingTypeService.findAll();

				return ResponseEntity.ok(listSettingType);
		}
		catch(Exception e){
			 
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
	@Transactional
	@GetMapping(value = "/setting/type/{id}")
    public ResponseEntity<?> getSettingType(@PathVariable String id)
	{
		try{
			SettingType settingType = settingTypeService.findById(id);

				return ResponseEntity.ok(settingType);
		}
		catch(Exception e){
			 
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
	@PostMapping(value = "/setting/type")
	@Transactional
    public ResponseEntity<?> postSettingType(@RequestBody SettingType settingType)
	{
		try{	
			settingTypeService.save(settingType);	
				return ResponseEntity.ok("Save Success");
		}
		catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

    }
	
	@Transactional
	@PutMapping(value = "/setting/type")
    public ResponseEntity<?> putSettingType(@RequestBody SettingType settingType)
	{
		try{	
			settingTypeService.update(settingType);	
			return ResponseEntity.ok("Put Success");
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

    }
	
	@Transactional
	@DeleteMapping("/setting/type/{id}")
    public ResponseEntity<?> deleteSettingType(@PathVariable String id)
	{
		try{	
			settingTypeService.delete(id);	
			return ResponseEntity.ok("Delete Success");
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

    }
}
