package com.demo.controller;

import com.demo.model.City;
import com.demo.model.Company;
import com.demo.service.CityService;
import com.demo.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Controller
@RequestMapping({"/api/v1/lov"})
public class LovController {

    @Autowired
    private CityService cityService;

    @Autowired
    private CompanyService companyService;

    @GetMapping(value = "/cities/{offset}/{limit}")
    public @ResponseBody List<City> getAllCity(
        @PathVariable Integer offset,
        @PathVariable Integer limit
    ){
        List<City> cityList = cityService.findAll(offset,limit);
        return cityList;
    }

    @GetMapping(value = "/companies")
    public @ResponseBody List<Company> getAllCompany(){
        List<Company> companyList = companyService.findAll();
        return companyList;
    }





//    public ResponseEntity<?> city() throws Exception{
//        try{
//            lovService.findAllCity();
//            return ResponseEntity.ok("Success!");
//        } catch (Exception e){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//        }
//    }


}
