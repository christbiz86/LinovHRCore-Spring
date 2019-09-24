package com.demo.controller;

import com.demo.model.Costcenter;
import com.demo.service.CostcenterService;
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

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@Transactional
@RequestMapping({"/api/v1"})
public class CostcenterController {

    @Autowired
    private CostcenterService costcenterService;

    @GetMapping(value = "/costcenter/{id}")
    public ResponseEntity<?> getCostcenterById(
            @PathVariable String id
    ){
        List<Costcenter> companyId = costcenterService.findById(id);
        return new ResponseEntity<List<Costcenter>>(companyId, HttpStatus.OK);
    }

}
