package com.demo.controller;


import com.demo.model.City;
import com.demo.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Controller
@Transactional
@RequestMapping({"/api/v1"})
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping(value = "/city/{id}")
    public ResponseEntity<?> getCityById(
            @PathVariable String id
    ){
        List<City> cityId = cityService.findById(id);
        return new ResponseEntity<List<City>>(cityId, HttpStatus.OK);
    }

}
