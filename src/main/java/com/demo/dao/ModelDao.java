package com.demo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.common.SimpleReportFiller;
import com.demo.model.Model;

@Repository("modelDao")
public class ModelDao extends CommonDao{
	
	@Transactional
	public void save(Model model) {
		super.entityManager.merge(model);
	}
	
	@Transactional
	public void delete(String id) {
		Model model = findById(id);
		super.entityManager.remove(model);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public Model findById(String id) {	
		List<Model> list = super.entityManager
                .createQuery("from Model where id=:id")
                .setParameter("id", id)
                .getResultList();
		if (list.size() == 0)
			return new Model();
		else
			return (Model)list.get(0);
	}
	
	
	@Transactional
	public SimpleReportFiller getReport() {
		SimpleReportFiller simpleReportFiller = new SimpleReportFiller();
		simpleReportFiller.setEntityManager(super.entityManager);
    
        simpleReportFiller.setReportFileName("modelReport.jrxml");
        simpleReportFiller.compileReport();
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("title", "This is a title");
        parameters.put("p_condition", "");
        parameters.put("p_sort", "");
        parameters.put("p_query", "SELECT ID, nama_model AS NAMA FROM tbl_model");

        simpleReportFiller.setParameters(parameters);
        simpleReportFiller.fillReport();
        return simpleReportFiller;
	}
	

}
