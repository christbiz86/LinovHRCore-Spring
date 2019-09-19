package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.UnitType;
import com.demo.service.UnitTypeService;

@RestController
@Controller
@RequestMapping({"/api/v1"})
public class UnitTypeController {
	@Autowired
	private UnitTypeService unitTypeService;
	
	@GetMapping(value = "/unit-type")
	public ResponseEntity<?> findAll(){
		List<UnitType> unitType = unitTypeService.findAll();
		return ResponseEntity.ok(unitType);
	}
}
