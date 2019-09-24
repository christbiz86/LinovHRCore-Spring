package com.demo.controller;

import com.demo.model.Country;
import com.demo.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@Controller
@RequestMapping({"/api/v1"})
@Transactional
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping(value = "/country/{id}")
    public ResponseEntity<?> getCountryById(
            @PathVariable String id
    ){
        List<Country> countryId = countryService.findById(id);
        return new ResponseEntity<List<Country>>(countryId, HttpStatus.OK);
    }

    @GetMapping(value = "/country/code/{code}")
    public ResponseEntity<?> getCountryCode(
            @PathVariable String code
    ){
        List<Country> countryCode = countryService.findByCode(code);
        return new ResponseEntity<List<Country>>(countryCode, HttpStatus.OK);
    }

}
