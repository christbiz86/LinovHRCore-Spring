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

import com.demo.model.Session;
import com.demo.service.SessionService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@RequestMapping({"/api/v1"})
public class SessionController {
	
	@Autowired
	private SessionService sessionService;
	
	@Transactional
	@GetMapping(value = "/sessions")
    public ResponseEntity<?> getAllSession()
	{
		try{
			List<Session> listSession = sessionService.findAll();

				return ResponseEntity.ok(listSession);
		}
		catch(Exception e){
			 
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
	@Transactional
	@GetMapping(value = "/session/{id}")
    public ResponseEntity<?> getSession(@PathVariable String id)
	{
		try{
			Session session = sessionService.findById(id);

				return ResponseEntity.ok(session);
		}
		catch(Exception e){
			 
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
	@Transactional
	@PostMapping("/session")
    public ResponseEntity<?> postSession(@RequestBody Session session)
	{
		try{	
			sessionService.save(session);	
			return ResponseEntity.ok("Save Success");
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insert Failed");
		}
    }
	
	@Transactional
	@PutMapping("/session")
    public ResponseEntity<?> putSession(@RequestBody Session session)
	{
		try{	
			sessionService.update(session);	
			return ResponseEntity.ok("Put Success");
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

    }

	@Transactional
	@DeleteMapping("/session/{id}")
    public ResponseEntity<?> deleteSession(@PathVariable String id)
	{
		try{	
			sessionService.delete(id);	
			return ResponseEntity.ok("Delete Success");
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

    }
}
