package com.demo.controller;

import com.demo.model.Province;
import com.demo.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
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
        List<Province> provinceList = provinceService.findAll();
        return new ResponseEntity<List<Province>>(provinceList, HttpStatus.OK);
    }

}
