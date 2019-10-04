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

import com.demo.model.Costcenter;
import com.demo.service.CostcenterService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@Transactional
@RequestMapping({"/api/v1"})
public class CostcenterController {

    @Autowired
    private CostcenterService costcenterService;
    
    @GetMapping(value = "/cost-centers")
    @Transactional
    public ResponseEntity<?> getAllCostcenter() throws Exception {
    	try {
    		List<Costcenter> list = costcenterService.findAll();
            return new ResponseEntity<List<Costcenter>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Retrieve failed!");
		}
    }

    @GetMapping(value = "/cost-center/{id}")
    @Transactional
    public ResponseEntity<?> getCostcenterById(@PathVariable String id) throws Exception {
    	try {
    		Costcenter companyId = costcenterService.findById(id);
            return new ResponseEntity<Costcenter>(companyId, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Retrieve failed!");
		}
    }
    
    @GetMapping(value = "/cost-center/code/{code}")
	@Transactional
	public ResponseEntity<?> findByCode(@PathVariable String code) throws Exception {
		try {
			Costcenter cc = costcenterService.findByCode(code);
			return new ResponseEntity<Costcenter>(cc, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Retrieve failed!");
		}
	}
	
	@PostMapping(value = "/cost-center")
	@Transactional
	public ResponseEntity<?> insert(@RequestBody Costcenter cc) throws Exception {
		try {
			costcenterService.insert(cc);
			return ResponseEntity.ok("Insert success with Cost Center name: "+cc.getName());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping(value = "/cost-center")
	@Transactional
	public ResponseEntity<?> update(@RequestBody Costcenter cc) throws Exception {
		try {
			costcenterService.update(cc);
			return ResponseEntity.ok("Update success with Cost Center ID: "+cc.getId());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@DeleteMapping(value = "/cost-center/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable String id) throws Exception {
		try {
			costcenterService.delete(id);
			return ResponseEntity.ok("Delete success with Cost Center ID: "+id);
		} catch (Exception e) {
		    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Delete failed!");
		}
	}

}
