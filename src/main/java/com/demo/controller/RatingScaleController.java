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

import com.demo.model.RatingScale;
import com.demo.model.RatingScaleDetail;
import com.demo.service.RatingScaleService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@RequestMapping({ "/api/v1" })
public class RatingScaleController {

	@Autowired
	private RatingScaleService ratingScaleService;

	@GetMapping(value = "/rating-scales")
	@Transactional
	public ResponseEntity<?> getRatingScales() {
		List<RatingScale> ratingScale = ratingScaleService.findAll();
		return ResponseEntity.ok(ratingScale);
	}
	
	@GetMapping(value = "/rating-scale/details")
	@Transactional
	public ResponseEntity<?> getRatingScaleDetails() {
		List<RatingScaleDetail> ratingScDet = ratingScaleService.findAllDet();
		return ResponseEntity.ok(ratingScDet);
	}

	@GetMapping(value = "/rating-scale/{id}")
	@Transactional
	public ResponseEntity<?> getRatingScaleById(@PathVariable String id) {
		RatingScale ratingScale = ratingScaleService.findById(id);
		return ResponseEntity.ok(ratingScale);
	}
	
	@GetMapping(value = "/rating-scale/detail/{id}")
	@Transactional
	public ResponseEntity<?> getRatingScaleDetailById(@PathVariable String id) {
		RatingScaleDetail ratingScale = ratingScaleService.findDetById(id);
		return ResponseEntity.ok(ratingScale);
	}

	@PostMapping(value = "/rating-scale")
	@Transactional
	public ResponseEntity<?> submitHeaderDetail(@RequestBody RatingScale ratingScale) throws Exception {
		try {
			ratingScaleService.saveHeaderDetail(ratingScale);
			return ResponseEntity.ok(HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} 
	}
	
	@PostMapping(value = "/rating-scale/header")
	@Transactional
	public ResponseEntity<?> submitHeader(@RequestBody RatingScale ratingScale) throws Exception {
		try {
			ratingScaleService.saveHeader(ratingScale);
			return ResponseEntity.ok(HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} 
	}
	
	@PostMapping(value = "/rating-scale/detail")
	@Transactional
	public ResponseEntity<?> submitDetail(@RequestBody RatingScaleDetail ratingScaleDet) throws Exception {
		try {
			ratingScaleService.saveDet(ratingScaleDet);
			return ResponseEntity.ok(HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} 
	}
	
	@PutMapping(value = "/rating-scale")
	@Transactional
	public ResponseEntity<?> updateHeader(@RequestBody RatingScale ratingScale) throws Exception {
		try {
			ratingScaleService.update(ratingScale);
			return ResponseEntity.ok(HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Update Failed!");
		}
	}
	
	@PutMapping(value = "/rating-scale/detail")
	@Transactional
	public ResponseEntity<?> updateDetail(@RequestBody RatingScaleDetail ratingScale) throws Exception {
		try {
			ratingScaleService.updateDet(ratingScale);
			return ResponseEntity.ok(HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Update Failed!");
		}
	}
	
	@DeleteMapping(value = "/rating-scale/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable String id) throws Exception {
		try {
			ratingScaleService.delete(id);
			return ResponseEntity.ok(HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Delete Failed!");
		}
	}

	@DeleteMapping(value = "/rating-scale/detail/{id}")
	@Transactional
	public ResponseEntity<?> deleteDetail(@PathVariable String id) throws Exception {
		try {
			ratingScaleService.deleteDet(id);
			return ResponseEntity.ok(HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Delete Failed!");
		}
	}
}
