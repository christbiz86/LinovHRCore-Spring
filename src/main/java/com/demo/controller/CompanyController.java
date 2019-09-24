package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.Company;
import com.demo.service.CompanyService;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@Transactional
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
