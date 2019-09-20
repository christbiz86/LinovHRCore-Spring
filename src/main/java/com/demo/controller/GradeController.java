package com.demo.controller;

import com.demo.exception.MessageResponse;
import com.demo.model.Grade;
import com.demo.service.GradeService;
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
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @GetMapping(value = "/grade/{id}")
    public ResponseEntity<?> getGradeById(
            @PathVariable String id
    ){
        Grade gradeList = gradeService.findById(id);
        return new ResponseEntity<Grade>(gradeList, HttpStatus.OK);
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
        gradeService.insert(grade);
        MessageResponse msg = new MessageResponse("Insert grade success");
        return ResponseEntity.status(HttpStatus.CREATED).body(msg);
    }

    @PutMapping(value = "/grade")
    public ResponseEntity<?> update(@RequestBody Grade grade) throws Exception{
        gradeService.update(grade);
        MessageResponse msg = new MessageResponse("Update grade success");
        return ResponseEntity.status(HttpStatus.OK).body(msg);
    }

    @DeleteMapping(value = "/grade/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) throws Exception{
        Grade gradeId = gradeService.findById(id);
        if(gradeId == null){
            MessageResponse msg = new MessageResponse("Grade ID is not found!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
        } else {
            gradeService.delete(id);
            MessageResponse msg = new MessageResponse("Grade deleted!");
            return ResponseEntity.status(HttpStatus.OK).body(msg);
        }
    }


}
