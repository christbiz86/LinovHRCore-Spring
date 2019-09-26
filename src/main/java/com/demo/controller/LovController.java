package com.demo.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.combo.CountryComboBean;
import com.demo.model.City;
import com.demo.model.Company;
import com.demo.model.Costcenter;
import com.demo.model.Country;
import com.demo.model.Grade;
import com.demo.model.Location;
import com.demo.service.CityService;
import com.demo.service.CompanyService;
import com.demo.service.CostcenterService;
import com.demo.service.CountryService;
import com.demo.service.GradeService;
import com.demo.service.LocationService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@Transactional
@RequestMapping({"/api/v1/lov"})
public class LovController {

    @Autowired
    private CityService cityService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CostcenterService costcenterService;

    @Autowired
    private GradeService gradeService;
    
    @Autowired
    private LocationService locationService;
    
    @Autowired
	private CountryComboBean ccb;

    @GetMapping(value = "/cities")
    @Transactional
    public ResponseEntity<?> getAllCity(){
        try {
        	List<City> cityList = cityService.findAll();
            return new ResponseEntity<List<City>>(cityList,HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Retrieve failed!");
		}
    }

    @GetMapping(value = "/companies")
    @Transactional
    public ResponseEntity<?> getAllCompany(){
        try {
        	List<Company> companyList = companyService.findAll();
            return new ResponseEntity<List<Company>>(companyList,HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Retrieve failed!");
		}
    }

    @GetMapping(value = "/cost-centers")
	@Transactional
	public ResponseEntity<?> findAll() {
		try {
			List<Costcenter> list = costcenterService.findAll();
			return new ResponseEntity<List<Costcenter>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Retrieve failed!");
		}
	}

    @GetMapping(value = "/countries")
    @PostConstruct
    public ResponseEntity<?> getAllCountries() {
    	try {
//    		List<Country> list = countryService.findAll();
    		List<Country> list = ccb.getList();
    		System.out.println(list.get(0).getCode());
			return new ResponseEntity<List<Country>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Retrieve failed!");
		}
    }

    @GetMapping(value = "/grades")
    @Transactional
    public ResponseEntity<?> getAllGrades() {
        List<Grade> gradeList = gradeService.findall();
        return new ResponseEntity<List<Grade>>(gradeList,HttpStatus.OK);
    }
    
    @GetMapping(value = "/locations")
    @Transactional
    public ResponseEntity<?> getAllLocation() {
    	try {
    		List<Location> locationList = locationService.findAll();
        	return new ResponseEntity<List<Location>>(locationList, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}	
    }

}
