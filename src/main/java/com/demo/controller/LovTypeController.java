package com.demo.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.LovType;
import com.demo.service.LovTypeService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@RequestMapping({"/api/v1/lovtype"})
public class LovTypeController {

 	@Autowired
 	private LovTypeService lovTypeService;
	   
 	@Transactional
	@GetMapping(value = "/{id}")
 	public ResponseEntity<?> getLovType(
           @PathVariable String id){
 		
	       LovType lovType = lovTypeService.findById(id);
	       return new ResponseEntity<LovType>(lovType, HttpStatus.OK);
   }
}