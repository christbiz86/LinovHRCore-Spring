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

import com.demo.model.WidgetType;
import com.demo.service.WidgetTypeService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@RequestMapping({"/api/v1"})
public class WidgetTypeController {

	@Autowired
	private WidgetTypeService widgetTypeService;
	
	@Transactional
	@GetMapping(value = "/widget-types")
    public ResponseEntity<?> getAllWidgetType()
	{
		try{
				List<WidgetType> listWidgetType = widgetTypeService.findAll();

				return ResponseEntity.ok(listWidgetType);
		}
		catch(Exception e){
			 
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
	@Transactional
	@GetMapping(value = "/widget-type/{id}")
    public ResponseEntity<?> getWidgetType(@PathVariable String id)
	{
		try{
				WidgetType widgetType = widgetTypeService.findById(id);

				return ResponseEntity.ok(widgetType);
		}
		catch(Exception e){
			 
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
	@Transactional
	@PostMapping("/widget-type")
    public ResponseEntity<?> postWidgetType(@RequestBody WidgetType widgetType)
	{
		try{	
			widgetTypeService.save(widgetType);	
			return ResponseEntity.ok("Save Success");
		}
		catch (Exception e) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
	@Transactional
	@PutMapping("/widget-type")
    public ResponseEntity<?> putWidgetType(@RequestBody WidgetType widgetType)
	{
		try{	
			widgetTypeService.update(widgetType);	
			return ResponseEntity.ok("Put Success");
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

    }
	
	@Transactional
	@DeleteMapping("/widget-type/{id}")
    public ResponseEntity<?> deleteWidgetType(@PathVariable String id)
	{
		try{	
			widgetTypeService.delete(id);	
			return ResponseEntity.ok("Delete Success");
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

    }
}
