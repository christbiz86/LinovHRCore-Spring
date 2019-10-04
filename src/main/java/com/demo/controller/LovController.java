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

import com.demo.combo.ActionTypeComboBean;
import com.demo.combo.AssignmentReasonComboBean;
import com.demo.combo.AssignmentStatusComboBean;
import com.demo.combo.BloodTypeComboBean;
import com.demo.combo.CityComboBean;
import com.demo.combo.CompanyComboBean;
import com.demo.combo.CostcenterComboBean;
import com.demo.combo.CountryComboBean;
import com.demo.combo.EmployeeComboBean;
import com.demo.combo.EmployeeStatusComboBean;
import com.demo.combo.EmployeeTypeComboBean;
import com.demo.combo.GenderComboBean;
import com.demo.combo.GradeComboBean;
import com.demo.combo.JobComboBean;
import com.demo.combo.LocationComboBean;
import com.demo.combo.MaritalComboBean;
import com.demo.combo.PaymentMethodComboBean;
import com.demo.combo.PositionComboBean;
import com.demo.combo.ReligionComboBean;
import com.demo.combo.UnitComboBean;
import com.demo.combo.ProjectComboBean;
import com.demo.combo.ProvinceComboBean;
import com.demo.combo.ReligionComboBean;
import com.demo.combo.UnitComboBean;
import com.demo.model.AssignmentReason;
import com.demo.model.City;
import com.demo.model.Company;
import com.demo.model.Costcenter;
import com.demo.model.Country;
import com.demo.model.Employee;
import com.demo.model.EmployeeStatus;
import com.demo.model.Grade;
import com.demo.model.Job;
import com.demo.model.Location;
import com.demo.model.Lov;
import com.demo.model.Position;
import com.demo.model.Project;
import com.demo.model.Province;
import com.demo.model.Unit;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@Transactional
@RequestMapping({ "/api/v1/lov" })
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
	private JobComboBean jobComboBean;

	@Autowired
	private AssignmentStatusComboBean asComboBean;
	
	@Autowired
	private EmployeeStatusComboBean esComboBean;
	
	@Autowired
	private AssignmentReasonComboBean arComboBean;
	
	@Autowired
	private ProvinceComboBean provinceComboBean;
    
    @Autowired
    private GenderComboBean genderComboBean;
    
    @Autowired
    private MaritalComboBean maritalComboBean;
    
    @Autowired
    private ActionTypeComboBean actionTypeComboBean;
    
    @Autowired
    private EmployeeTypeComboBean employeeTypeComboBean;

    @Autowired
    private UnitComboBean unitComboBean;
    
    @Autowired
    private PositionComboBean posComboBean;
    
    @Autowired
    private BloodTypeComboBean bloodTypeComboBean;
    
    @Autowired
    private EmployeeComboBean employeeComboBean;

    @Autowired
    private ReligionComboBean religionComboBean;
    
    @Autowired
    private PaymentMethodComboBean paymentMethodComboBean;
    
    @Autowired
    private ProjectComboBean projectComboBean;

    @GetMapping(value = "/cities")
    @Transactional
    public ResponseEntity<?> getAllCity(){
        try {
        	List<City> cityList = cityComboBean.getList();
            return new ResponseEntity<List<City>>(cityList,HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@GetMapping(value = "/companies")
	@Transactional
	public ResponseEntity<?> getAllCompany() throws Exception {
		try {
			List<Company> companyList = companyComboBean.getList();
			return new ResponseEntity<List<Company>>(companyList, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@GetMapping(value = "/cost-centers")
	@Transactional
	public ResponseEntity<?> findAll() throws Exception {
		try {
			List<Costcenter> list = ccComboBean.getList();
			return new ResponseEntity<List<Costcenter>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@GetMapping(value = "/countries")
	@PostConstruct
	public ResponseEntity<?> getAllCountries() throws Exception {
		try {
			List<Country> list = countryComboBean.getList();
			return new ResponseEntity<List<Country>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@GetMapping(value = "/grades")
	@Transactional
	public ResponseEntity<?> getAllGrades() throws Exception {
		try {
			List<Grade> gradeList = gradeComboBean.getList();
			return new ResponseEntity<List<Grade>>(gradeList, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@GetMapping(value = "/locations")
	@Transactional
	public ResponseEntity<?> getAllLocation() throws Exception {
		try {
			List<Location> locationList = locationComboBean.getList();
			return new ResponseEntity<List<Location>>(locationList, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@GetMapping(value = "/assignment-statuses")
	@Transactional
	public ResponseEntity<?> getAllAssignmentStatusLov(String code) throws Exception {
		try {
			List<Lov> list = asComboBean.getList();
			return new ResponseEntity<List<Lov>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@Transactional
	@GetMapping(value = "/employee-statuses")
	public ResponseEntity<?> getAllEmployeeStatusLov() {
		try {
			List<EmployeeStatus> list = esComboBean.getList();
			return ResponseEntity.ok(list);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping(value = "/assignment-reasons")
	@Transactional
	public ResponseEntity<?> getAllAssignmentReasonLov() throws Exception {
		try {
			List<AssignmentReason> list = arComboBean.getList();
			return new ResponseEntity<List<AssignmentReason>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@GetMapping(value = "/provinces")
	@Transactional
	public ResponseEntity<?> getAllProvinceLov() throws Exception {
		try {
			List<Province> list = provinceComboBean.getList();
			return new ResponseEntity<List<Province>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

    @GetMapping(value = "/genders")
    @Transactional
    public ResponseEntity<?> getAllGender() {
        try {
            List<Lov> genderList = genderComboBean.getList();
            return ResponseEntity.ok(genderList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping(value = "/maritals")
    @Transactional
    public ResponseEntity<?> getAllMaritals() {
    	try {
    		List<Lov> maritalList = maritalComboBean.getList();
        	return ResponseEntity.ok(maritalList);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
    
    @GetMapping(value = "/action-types")
    @Transactional
    public ResponseEntity<?> getAllActionType() {
    	try {
    		List<Lov> actionTypeList = actionTypeComboBean.getList();
        	return ResponseEntity.ok(actionTypeList);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
    
    @GetMapping(value = "/employee-types")
    @Transactional
    public ResponseEntity<?> getAllEmployeeType() {
    	try {
    		List<Lov> empTypeList = employeeTypeComboBean.getList();
        	return ResponseEntity.ok(empTypeList);
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
    
    @GetMapping(value = "/employees")
    @Transactional
    public ResponseEntity<?> getAllEmployees() {
    	try {
    		List<Employee> empList = employeeComboBean.getList();
        	return new ResponseEntity<List<Employee>>(empList, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }

    @GetMapping(value = "/religions")
    @Transactional
    public ResponseEntity<?> getReligions() {
    	try {
    		List<Lov> lovList= religionComboBean.getListReligion();

    		return new ResponseEntity<List<Lov>>(lovList, HttpStatus.OK); 
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
    
    @GetMapping(value = "/payment-methods")
    @Transactional
    public ResponseEntity<?> getPaymentMethods() {
    	try {
    		List<Lov> lovList= paymentMethodComboBean.getListPaymentMethod();   			

    		return new ResponseEntity<List<Lov>>(lovList, HttpStatus.OK); 
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }

    @GetMapping(value = "/projects")
	@Transactional
	public ResponseEntity<?> getAllProject() {
		try {
			List<Project> projectList = projectComboBean.getList();
			return new ResponseEntity<List<Project>>(projectList, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
    
}
