package com.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.City;
import com.demo.service.CityService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@Transactional
@RequestMapping({"/api/v1"})
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping(value = "/city/{id}")
    @Transactional
    public ResponseEntity<?> getCityById(
            @PathVariable String id
    ){
    	try {
    		City city = cityService.findById(id);
            return ResponseEntity.ok(city);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }

    @PostMapping(value = "/city")
    @Transactional
    public ResponseEntity<?> submit(@RequestBody City city) throws Exception {
    	try {
			cityService.save(city);
			return ResponseEntity.ok("Data Have Successfully Saved");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
    
    @PutMapping(value = "/city")
    @Transactional
    public ResponseEntity<?> update(@RequestBody City city) throws Exception {
    	try {
			cityService.update(city);
			return ResponseEntity.ok("Data Have Successfully Updated");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
    
    @DeleteMapping(value = "/city/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable String id) throws Exception {
    	try {
    		cityService.delete(id);
    		return ResponseEntity.ok("Data Have Successfully Deleted");
    	} catch (Exception e) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    	}
    }
}
