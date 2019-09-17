package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.exception.ErrorException;
import com.demo.model.Model;
import com.demo.service.ModelService;

@RestController
@RequestMapping("/model")
public class ModelController {

	@Autowired
	private ModelService modelService;
	
	
	@PostMapping("") 
	public ResponseEntity<?> insert(@RequestBody Model model) throws ErrorException {
		try {
			modelService.insertModel(model);
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.CREATED).body("Model Berhasil Ditambah");
	}

	@PutMapping("")
	public ResponseEntity<?> update(@RequestBody Model model) throws ErrorException {
		try {
			modelService.updateModel(model);
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body("Model Berhasil Diperbarui");
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable String id) {
		try {
			modelService.deleteModel(id);
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body("Model Berhasil Dihapus");
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<?> getById(@PathVariable String id) {
		return ResponseEntity.ok(modelService.findById(id));
	}
	
	@GetMapping("/report")
	public ResponseEntity<?> report(){
		String result = modelService.getReport();
		if (result.equals("OK")) {
			return ResponseEntity.status(HttpStatus.OK).body("Check Report Now");
		} else {
			return ResponseEntity.status(HttpStatus.OK).body("Ada kesalahan dalam report");
		}
		
	}

}