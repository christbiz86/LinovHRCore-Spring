package com.demo.controller;

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

import com.demo.model.WorkList;
import com.demo.service.WorkListService;

@RestController
@Controller
@RequestMapping({"/api/v1"})
public class WorkListController {
	
	@Autowired
	private WorkListService workListService;
	
	@Transactional
	@GetMapping(value = "worklist/{id}")
    public ResponseEntity<?> getWorkList(@PathVariable String id)
	{
		try{
				WorkList workList = workListService.findById(id);

				return ResponseEntity.ok(workList);
		}
		catch(Exception e){
			 
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
	@Transactional
	@PostMapping("/worklist")
    public ResponseEntity<?> postWorkList(@RequestBody WorkList workList)
	{
		try{	
//			workListService.save(workList);	
			return ResponseEntity.ok("Save Success");
		}
		
		catch (Exception e) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
	@Transactional
	@PutMapping("/worklist")
    public ResponseEntity<?> putWorkList(@RequestBody WorkList workList)
	{
		try{	
//			workListService.update(workList);	
			return ResponseEntity.ok("Put Success");
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

    }

	@Transactional
	@DeleteMapping("/worklist/{id}")
    public ResponseEntity<?> deleteWorkList(@PathVariable String id)
	{
		try{	
			workListService.delete(id);	
			return ResponseEntity.ok("Delete Success");
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

    }
}
