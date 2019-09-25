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

import com.demo.model.PersonAddress;
import com.demo.service.PersonAddressService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@RequestMapping({"/api/v1"})
public class PersonAddressController {

	@Autowired
	private PersonAddressService personAddressService;
	
	@Transactional
	@GetMapping(value = "/person/{id}/addresses")
    public ResponseEntity<?> getPersonAddress(@PathVariable String id)
	{
		try{
				List<PersonAddress> listPersonAddress = personAddressService.findByPersonId(id);

				return ResponseEntity.ok(listPersonAddress);
		}
		catch(Exception e){
			 
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	

	@PostMapping(value = "/person/address")
	@Transactional
    public ResponseEntity<?> postPersonAddress(@RequestBody PersonAddress personAddress)
	{
		try{	
				personAddressService.save(personAddress);	
				return ResponseEntity.ok("Save Success");
		}
		catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

    }
	
	@Transactional
	@PutMapping(value = "/person/{id}/address")
    public ResponseEntity<?> putPersonAddress(@PathVariable String id,@RequestBody PersonAddress personAddress)
	{
		try{	
				personAddressService.update(personAddress);	
				return ResponseEntity.ok("Put Success");
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

    }
	
	@Transactional
	@DeleteMapping("/person/address/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id)
	{
		try{	
			personAddressService.delete(id);	
			return ResponseEntity.ok("Delete Success");
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

    }
}
