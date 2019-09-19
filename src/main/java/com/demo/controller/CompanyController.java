package com.demo.controller;

import com.demo.model.Company;
import com.demo.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Controller
@RequestMapping({"/api/v1"})
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping(value = "/company/{id}")
    public ResponseEntity<?> getCompanyById(
            @PathVariable String id
    ){
        List<Company> companyId = companyService.findById(id);
        return new ResponseEntity<List<Company>>(companyId, HttpStatus.OK);
    }

}
