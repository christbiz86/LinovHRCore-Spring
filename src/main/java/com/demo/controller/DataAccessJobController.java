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

import com.demo.exception.ValidationException;
import com.demo.model.DataAccessJob;
import com.demo.service.DataAccessJobService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@RequestMapping({ "/api/v1" })
public class DataAccessJobController {

	@Autowired
	private DataAccessJobService dataAccessJobService;

	@GetMapping(value = "/data-access-jobs")
	@Transactional
	public ResponseEntity<?> getDataAccessJobs() {
		List<DataAccessJob> positionList = dataAccessJobService.findAll();
		return ResponseEntity.ok(positionList);
	}

	@GetMapping(value = "/data-access-job/{id}")
	@Transactional
	public ResponseEntity<?> getDataAccessJobsById(@PathVariable String id) {
		DataAccessJob dataAccessJob = dataAccessJobService.findById(id);
		return ResponseEntity.ok(dataAccessJob);
	}

	@PostMapping(value = "/data-access-job")
	@Transactional
	public ResponseEntity<?> submit(@RequestBody DataAccessJob dataAccessJob) throws Exception {
		try {
			dataAccessJobService.save(dataAccessJob);
			return ResponseEntity.ok(HttpStatus.CREATED);
		} catch (ValidationException ex) {
			System.out.println(ex);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessages());
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} 
	}
	
	@PutMapping(value = "/data-access-job")
	@Transactional
	public ResponseEntity<?> update(@RequestBody DataAccessJob dataAccessJob) throws Exception {
		try {
			dataAccessJobService.update(dataAccessJob);
			return ResponseEntity.ok(HttpStatus.OK);
		} catch (ValidationException ex) {
			System.out.println(ex);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessages());
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Update Failed!");
		}
	}
	
	@DeleteMapping(value = "/data-access-job/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable String id) throws Exception {
		try {
			dataAccessJobService.delete(id);
			return ResponseEntity.ok(HttpStatus.OK);
		} catch (ValidationException ex) {
			System.out.println(ex);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessages());
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Delete Failed!");
		}
	}
}
