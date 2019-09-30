package com.demo.controller;

import com.demo.model.Province;
import com.demo.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Controller
@Transactional
@RequestMapping({"/api/v1"})
public class ProvinceController {

    @Autowired
    private ProvinceService provinceService;

    @GetMapping(value = "/provinces")
    public ResponseEntity<?> getAll(){
        try {
        	List<Province> provinceList = provinceService.findAll();
            return new ResponseEntity<List<Province>>(provinceList, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Retrieve failed!");
		}
    }
    
    @GetMapping(value = "/province/{id}")
	@Transactional
	public ResponseEntity<?> findById(@PathVariable String id) throws Exception {
		try {
			Province province = provinceService.findById(id);
			return new ResponseEntity<Province>(province, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Retrieve failed!");
		}
	}
	
	@GetMapping(value = "/province/code/{code}")
	@Transactional
	public ResponseEntity<?> findByCode(@PathVariable String code) throws Exception {
		try {
			Province province = provinceService.findByCode(code);
			return new ResponseEntity<Province>(province, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Retrieve failed!");
		}
	}
	
	@PostMapping(value = "/province")
	@Transactional
	public ResponseEntity<?> insert(@RequestBody Province province) throws Exception {
		try {
			provinceService.insert(province);
			return ResponseEntity.ok("Insert success with Province name: "+province.getName());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping(value = "/province")
	@Transactional
	public ResponseEntity<?> update(@RequestBody Province province) throws Exception {
		try {
			provinceService.update(province);
			return ResponseEntity.ok("Update success with Province ID: "+province.getId());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@DeleteMapping(value = "/province/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable String id) throws Exception {
		try {
			provinceService.delete(id);
			return ResponseEntity.ok("Delete success with Province ID: "+id);
		} catch (Exception e) {
		    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Delete failed!");
		}
	}    

}
