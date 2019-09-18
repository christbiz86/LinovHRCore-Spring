package com.demo.controller;

import com.demo.model.Grade;
import com.demo.service.GradeService;
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

@RestController
@Controller
@Transactional
@RequestMapping({"/api/v1"})
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @GetMapping(value = "/grade/{id}")
    public ResponseEntity<?> getGradeById(
            @PathVariable String id
    ){
        List<Grade> gradeList = gradeService.findById(id);
        return new ResponseEntity<List<Grade>>(gradeList, HttpStatus.OK);
    }

    @GetMapping(value = "/grade/code/{id}")
    public ResponseEntity<?> getGradeByCode(
            @PathVariable String code
    ){
        List<Grade> gradeList = gradeService.findByCode(code);
        return new ResponseEntity<List<Grade>>(gradeList, HttpStatus.OK);
    }

}
