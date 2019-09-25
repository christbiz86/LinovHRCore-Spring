package com.demo.controller;

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

import com.demo.model.Company;
import com.demo.service.CompanyService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@RequestMapping({"/api/v1"})
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping(value = "/company/{id}")
    @Transactional
    public ResponseEntity<?> getCompanyById(@PathVariable String id){
        Company company = companyService.findById(id);
        return ResponseEntity.ok(company);
    }
    
    @PostMapping(value = "/company")
    @Transactional
    public ResponseEntity<?> submit(@RequestBody Company company) throws Exception {
    	try {
			companyService.save(company);
			return ResponseEntity.ok("Data Have Successfully Saved");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }

    @PutMapping(value = "/company")
    @Transactional
    public ResponseEntity<?> update(@RequestBody Company company) throws Exception {
    	try {
			companyService.update(company);
			return ResponseEntity.ok("Data Have Successfull Updated");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
    
    @DeleteMapping(value = "/company/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable String id) throws Exception {
    	try {
    		companyService.delete(id);
    		return ResponseEntity.ok("Data Have Successfully Deleted");
    	} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
}
