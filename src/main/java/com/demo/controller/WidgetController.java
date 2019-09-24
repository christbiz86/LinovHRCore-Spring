package com.demo.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.exception.ValidationException;
import com.demo.model.Widget;
import com.demo.service.WidgetService;

@RestController
@Controller
@RequestMapping({"/api/v1"})
public class WidgetController {
	
	@Autowired
	private WidgetService widgetService;
	
	@Transactional
	@GetMapping(value = "/widgets")
    public ResponseEntity<?> getAllWidget()
	{
		try{
				List<Widget> listWidget = widgetService.findAll();

				return ResponseEntity.ok(listWidget);
		}
		catch(Exception e){
			 
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
	@Transactional
	@GetMapping(value = "/widget/{id}")
    public ResponseEntity<?> getWidget(@PathVariable String id)
	{
		try{
				Widget widget = widgetService.findById(id);

				return ResponseEntity.ok(widget);
		}
		catch(Exception e){
			 
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
	@Transactional
	@PostMapping("/widget")
    public ResponseEntity<?> postWidget(@RequestBody Widget widget)
	{
		try{	
			widgetService.save(widget);	
			return ResponseEntity.ok("Save Success");
		}
		catch(ValidationException val){
			 
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(val.getMessage());
		}
		catch (Exception e) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
	@Transactional
	@PutMapping("/widget")
    public ResponseEntity<?> putWidget(@RequestBody Widget widget)
	{
		try{	
			widgetService.update(widget);	
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
	@DeleteMapping("/widget/{id}")
    public ResponseEntity<?> deleteWidget(@PathVariable String id)
	{
		try{	
			widgetService.delete(id);	
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
