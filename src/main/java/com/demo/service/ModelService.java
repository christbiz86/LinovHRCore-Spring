package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.common.SimpleReportExporter;
import com.demo.dao.ModelDao;
import com.demo.exception.ErrorException;
import com.demo.model.Model;

@Service("modelService")
public class ModelService {

	@Autowired
	private ModelDao modelDao;

	public Model findById(String id) {
		Model model = modelDao.findById(id);
		return model;
	}

	public void insertModel(Model model) throws ErrorException {
		modelDao.save(model);
	}
	
	public void updateModel(Model model) throws ErrorException {
		modelDao.save(model);
	}
	
	public void deleteModel(String id) throws ErrorException {
		modelDao.delete(id);
	}
	
	public String getReport() {
		SimpleReportExporter simpleExporter = new SimpleReportExporter();
		try {
			simpleExporter.setJasperPrint(modelDao.getReport().getJasperPrint());
			simpleExporter.exportToHtml("modelReport.html");
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return "Error";
		}
		
		return "OK";
	}
	
}
