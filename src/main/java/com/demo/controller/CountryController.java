package com.demo.controller;

import java.util.List;

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

import com.demo.model.Country;
import com.demo.service.CountryService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@RequestMapping({"/api/v1"})
@Transactional
public class CountryController {

    @Autowired
    private CountryService countryService;

//    @GetMapping(value = "/lov/countries")
//	@Transactional
//	public ResponseEntity<?> findAll() throws Exception {
//		try {
//			List<Country> list = countryService.findAll();
//			return new ResponseEntity<List<Country>>(list, HttpStatus.OK);
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Retrieve failed!");
//		}
//	}

    @GetMapping(value = "/country/{id}")
    public ResponseEntity<?> getCountryById(@PathVariable String id) throws Exception {
        try {
        	Country countryId = countryService.findById(id);
            return new ResponseEntity<Country>(countryId, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Retrieve failed!");
		}
    }

    @GetMapping(value = "/country/code/{code}")
    public ResponseEntity<?> getCountryCode(@PathVariable String code) throws Exception {
        try {
        	Country countryCode = countryService.findByBk(code);
            return new ResponseEntity<Country>(countryCode, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Retrieve failed!");
		}
    }
    
    @PostMapping(value = "/country")
	@Transactional
	public ResponseEntity<?> insert(@RequestBody Country country) throws Exception {
		try {
			countryService.insert(country);
			return ResponseEntity.ok("Insert success with Country name: "+country.getName());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping(value = "/country")
	@Transactional
	public ResponseEntity<?> update(@RequestBody Country country) throws Exception {
		try {
			countryService.update(country);
			return ResponseEntity.ok("Update success with Country ID: "+country.getId());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@DeleteMapping(value = "/country/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable String id) throws Exception {
		try {
			countryService.delete(id);
			return ResponseEntity.ok("Delete success with Country ID: "+id);
		} catch (Exception e) {
		    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Delete failed!");
		}
	}

}
