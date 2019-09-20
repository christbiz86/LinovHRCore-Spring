package com.demo.controller;

import com.demo.exception.ErrorException;
import com.demo.exception.MessageResponse;
import com.demo.model.Grade;
import com.demo.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @PostMapping(value = "/grade")
    public ResponseEntity<?> insert(@RequestBody Grade grade) throws Exception {
//        gradeService.saveAndFlush
        gradeService.insert(grade);
        MessageResponse msg = new MessageResponse("Insert success");
        return ResponseEntity.status(HttpStatus.CREATED).body(msg);
    }

}
