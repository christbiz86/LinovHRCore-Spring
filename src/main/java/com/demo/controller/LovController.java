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

import com.demo.combo.BloodTypeComboBean;
import com.demo.combo.CityComboBean;
import com.demo.combo.CompanyComboBean;
import com.demo.combo.CostcenterComboBean;
import com.demo.combo.CountryComboBean;
import com.demo.combo.EmployeeComboBean;
import com.demo.combo.GradeComboBean;
import com.demo.combo.JobComboBean;
import com.demo.combo.LocationComboBean;
import com.demo.combo.PositionComboBean;
import com.demo.combo.UnitComboBean;
import com.demo.model.City;
import com.demo.model.Company;
import com.demo.model.Costcenter;
import com.demo.model.Country;
import com.demo.model.Employee;
import com.demo.model.Grade;
import com.demo.model.Job;
import com.demo.model.Location;
import com.demo.model.Lov;
import com.demo.model.Position;
import com.demo.model.Unit;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@Transactional
@RequestMapping({"/api/v1/lov"})
public class LovController {
    
    @Autowired
	private CountryComboBean countryComboBean;
    
    @Autowired
    private CityComboBean cityComboBean;
    
    @Autowired
    private CompanyComboBean companyComboBean;
    
    @Autowired
    private CostcenterComboBean ccComboBean;
    
    @Autowired
    private GradeComboBean gradeComboBean;
    
    @Autowired
    private LocationComboBean locationComboBean;
    
    @Autowired
    private UnitComboBean unitComboBean;
    
    @Autowired
    private JobComboBean jobComboBean;
    
    @Autowired
    private PositionComboBean posComboBean;
    
    @Autowired
    private BloodTypeComboBean bloodTypeComboBean;
    
//    @Autowired
//    private EmployeeComboBean employeeComboBean;

    @GetMapping(value = "/cities")
    @Transactional
    public ResponseEntity<?> getAllCity(){
        try {
        	List<City> cityList = cityComboBean.getList();
            return new ResponseEntity<List<City>>(cityList,HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Retrieve failed!");
		}
    }

    @GetMapping(value = "/companies")
    @Transactional
    public ResponseEntity<?> getAllCompany(){
        try {
        	List<Company> companyList = companyComboBean.getList();
            return new ResponseEntity<List<Company>>(companyList,HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Retrieve failed!");
		}
    }

    @GetMapping(value = "/cost-centers")
	@Transactional
	public ResponseEntity<?> findAll() {
		try {
			List<Costcenter> list = ccComboBean.getList();
			return new ResponseEntity<List<Costcenter>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Retrieve failed!");
		}
	}

    @GetMapping(value = "/countries")
    @PostConstruct
    public ResponseEntity<?> getAllCountries() {
    	try {
    		List<Country> list = countryComboBean.getList();
			return new ResponseEntity<List<Country>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Retrieve failed!");
		}
    }

    @GetMapping(value = "/grades")
    @Transactional
    public ResponseEntity<?> getAllGrades() {
        List<Grade> gradeList = gradeComboBean.getList();
        return new ResponseEntity<List<Grade>>(gradeList,HttpStatus.OK);
    }
    
    @GetMapping(value = "/locations")
    @Transactional
    public ResponseEntity<?> getAllLocation() {
    	try {
    		List<Location> locationList = locationComboBean.getList();
        	return new ResponseEntity<List<Location>>(locationList, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}	
    }

    @GetMapping(value = "/units")
    @Transactional
    public ResponseEntity<?> getAllUnits() {
    	try {
    		List<Unit> unitList = unitComboBean.getList();
        	return new ResponseEntity<List<Unit>>(unitList, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}	
    }
    
    @GetMapping(value = "/jobs")
    @Transactional
    public ResponseEntity<?> getAllJobs() {
    	try {
    		List<Job> jobList = jobComboBean.getList();
        	return new ResponseEntity<List<Job>>(jobList, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}	
    }
    
    @GetMapping(value = "/positions")
    @Transactional
    public ResponseEntity<?> getAllPositions() {
    	try {
    		List<Position> posList = posComboBean.getList();
        	return new ResponseEntity<List<Position>>(posList, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}	
    }
    
    @GetMapping(value = "/bloods")
    @Transactional
    public ResponseEntity<?> getAllBloodTypes() {
    	try {
    		List<Lov> bloodList = bloodTypeComboBean.getList();
        	return new ResponseEntity<List<Lov>>(bloodList, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}	
    }
    
//    @GetMapping(value = "/employees")
//    @Transactional
//    public ResponseEntity<?> getAllEmployees() {
//    	try {
//    		List<Employee> empList = employeeComboBean.getList();
//        	return new ResponseEntity<List<Employee>>(empList, HttpStatus.OK);
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//		}	
//    }
}
