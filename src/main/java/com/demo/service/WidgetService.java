package com.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.WidgetDao;
import com.demo.exception.ValidationException;
import com.demo.model.Widget;

@Service
public class WidgetService {
	
	@Autowired
	private WidgetDao widgetDao;

	public List<Widget> findAll(){
        return widgetDao.findAll();
    }
	
	public List<Widget> findAll(Integer offset,Integer limit){
        return widgetDao.findAll(offset, limit);
    }
	
	public Widget findById(String id){
        return widgetDao.findOne(id);
    }
	
	public Widget findByBk(String name){
		Widget widget=new Widget();
		widget.setName(name);
		return widgetDao.findByBk(widget);
    }
	
	public void save(Widget widget) throws ValidationException {
    	valBkNotNull(widget);
		valBkNotExist(widget);
		valNonBk(widget);
		widgetDao.create(widget);
	}
	
	public void update(Widget widget) throws ValidationException {
		valIdNotNull(widget);
		valIdExist(widget.getId());
		valBkNotNull(widget);
		valBkNotChange(widget);
		valNonBk(widget);
		widgetDao.update(widget);
	}
	
	public void delete(String id) throws ValidationException {
		valIdExist(id);
		widgetDao.deleteById(id);
	}
	
	private void valIdExist(String id)throws ValidationException{
		if(!widgetDao.isIdExist(id)) {
			throw new ValidationException("Data tidak ada");
		}
	}
	
	private void valIdNotNull(Widget widget)throws ValidationException {
		if(widget.getId()==null) {
			throw new ValidationException("Id tidak boleh kosong");
		}
	}
	
	private void valNonBk(Widget widget)throws ValidationException{
		List<String> listErr = new ArrayList<String>();
		if(widget.getDescription().isEmpty()) {
			listErr.add("Description tidak boleh kosong");
		}
		if(widget.getAppCode().isEmpty()) {
			listErr.add("Appcode tidak boleh kosong");
		}
		if(widget.getWidgetType().getId().isEmpty()) {
			listErr.add("widget type id tidak boleh kosong");
		}
		if(widget.getParamIn().isEmpty()) {
			listErr.add("Param In tidak boleh kosong");
		}
		if(widget.getParamOut().isEmpty()) {
			listErr.add("Param Out tidak boleh kosong");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	private void valBkNotExist(Widget widget)throws ValidationException{
		if(widgetDao.isBkExist(widget)) {
			throw new ValidationException("Data sudah ada");
		}
	}	
	
	private void valBkNotChange(Widget widget)throws ValidationException{
		Widget tempWidget=findById(widget.getId());

		if(!tempWidget.getName().equals(widget.getName())) {
			throw new ValidationException("BK tidak boleh berubah");
		}
	}
	
	private void valBkNotNull(Widget widget) throws ValidationException{
		if(widget.getName().isEmpty() ) {
			throw new ValidationException("Bk tidak boleh kosong");
		}
	}
	
}
