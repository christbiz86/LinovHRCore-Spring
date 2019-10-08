package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.WidgetTypeDao;
import com.demo.model.WidgetType;

@Service
public class WidgetTypeService {

	@Autowired
	private WidgetTypeDao widgetTypeDao;
	
	public List<WidgetType> findAll(){
        return widgetTypeDao.findAll();
    }
	
	public WidgetType findById(String id){
        return widgetTypeDao.findOne(id);
    }
	
	public WidgetType findByBk(String name){
		return widgetTypeDao.findByBk(name);
    }
	
	public void save(WidgetType widgetType) throws Exception {
		valBkNotNull(widgetType);
		valBkNotExist(widgetType);
		valNonBk(widgetType);
		widgetTypeDao.create(widgetType);
	}
	
	public void update(WidgetType widgetType) throws Exception {
		valIdNotNull(widgetType);
		valIdExist(widgetType.getId());
		valBkNotNull(widgetType);
		valBkNotChange(widgetType);
		valNonBk(widgetType);
		widgetTypeDao.update(widgetType);
	}
	
	public void delete(String id) throws Exception {
		valIdExist(id);
		widgetTypeDao.deleteById(id);
	}
	
	private void valIdExist(String id)throws Exception{
		if(!widgetTypeDao.isIdExist(id)) {
			throw new Exception("Data tidak ada");
		}
	}
	
	private void valIdNotNull(WidgetType widgetType)throws Exception {
		if(widgetType.getId()==null) {
			throw new Exception("Id tidak boleh kosong");
		}
	}
	
	private void valNonBk(WidgetType widgetType)throws Exception{
		if(widgetType.getColSize() == null) {
			throw new Exception("Column tidak boleh kosong");
		}
		if(widgetType.getRowSize() == null) {
			throw new Exception("Row tidak boleh kosong");
		}
	}
	
	private void valBkNotExist(WidgetType widgetType)throws Exception{
		if(widgetTypeDao.isBkExist(widgetType)) {
			throw new Exception("Data sudah ada");
		}
	}	
	
	private void valBkNotChange(WidgetType widgetType)throws Exception{
		WidgetType tempWidgetType=findById(widgetType.getId());

		if(!tempWidgetType.getName().equals(widgetType.getName()) ) {
			throw new Exception("BK tidak boleh berubah");
		}
	}
	
	private void valBkNotNull(WidgetType widgetType) throws Exception{
		if(widgetType.getName().isEmpty() ) {
			throw new Exception("Bk tidak boleh kosong");
		}
	}
	
}
