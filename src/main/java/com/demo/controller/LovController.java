package com.demo.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

@RestController
@Controller
@Transactional
@RequestMapping({"/api/v1/lov"})
public class LovController {

    @Autowired
//    private CityComboBean cityComboBean;
    private CityService cityService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CostcenterService costcenterService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private GradeService gradeService;
    
    @Autowired
    private LocationService locationService;

    @GetMapping(value = "/cities/{offset}/{limit}")
    public ResponseEntity<?> getAllCity(
        @PathVariable Integer offset,
        @PathVariable Integer limit
    ){
        List<City> cityList = cityService.findAll(offset,limit);
        return new ResponseEntity<List<City>>(cityList,HttpStatus.OK);
    }

    @GetMapping(value = "/companies")
    public ResponseEntity<?> getAllCompany(){
        List<Company> companyList = companyService.findAll();
        return new ResponseEntity<List<Company>>(companyList,HttpStatus.OK);
    }

    @GetMapping(value = "/costcenters")
    public ResponseEntity<?> getAllCostcenters(){
        List<Costcenter> costcenterList = costcenterService.findAll();
        return new ResponseEntity<List<Costcenter>>(costcenterList,HttpStatus.OK);
    }

    @GetMapping(value = "/countries")
    public ResponseEntity<?> getAllCountries(){
        List<Country> countryList = countryService.findAll();
        return new ResponseEntity<List<Country>>(countryList,HttpStatus.OK);
    }

    @GetMapping(value = "/grades")
    public ResponseEntity<?> getAllGrades(){
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
