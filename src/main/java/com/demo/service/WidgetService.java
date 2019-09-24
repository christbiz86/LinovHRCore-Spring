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
	
	public void save(Widget widget) throws Exception {
    	valBkNotNull(widget);
		valBkNotExist(widget);
		valNonBk(widget);
		widgetDao.create(widget);
	}
	
	public void update(Widget widget) throws Exception {
		valIdNotNull(widget);
		valIdExist(widget.getId());
		valBkNotNull(widget);
		valBkNotChange(widget);
		valNonBk(widget);
		widgetDao.update(widget);
	}
	
	public void delete(String id) throws Exception {
		valIdExist(id);
		widgetDao.deleteById(id);
	}
	
	private void valIdExist(String id)throws Exception{
		if(!widgetDao.isIdExist(id)) {
			throw new Exception("Data tidak ada");
		}
	}
	
	private void valIdNotNull(Widget widget)throws Exception {
		if(widget.getId()==null) {
			throw new Exception("Id tidak boleh kosong");
		}
	}
	
	private void valNonBk(Widget widget)throws Exception{
		List<String> listErr = new ArrayList<String>();
		if(widget.getDescription() == null) {
			listErr.add("Description tidak boleh kosong");
		}
		if(widget.getAppCode()==null) {
			listErr.add("Appcode tidak boleh kosong");
		}
		if(widget.getWidgetType().getId()==null) {
			listErr.add("widget type id tidak boleh kosong");
		}
		if(widget.getParamIn()==null) {
			listErr.add("Param In tidak boleh kosong");
		}
		if(widget.getParamOut()==null) {
			listErr.add("Param Out tidak boleh kosong");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	private void valBkNotExist(Widget widget)throws Exception{
		if(widgetDao.isBkExist(widget)) {
			throw new Exception("Data sudah ada");
		}
	}	
	
	private void valBkNotChange(Widget widget)throws Exception{
		Widget tempWidget=findById(widget.getId());

		if(!tempWidget.getName().equals(widget.getName())) {
			throw new Exception("BK tidak boleh berubah");
		}
	}
	
	private void valBkNotNull(Widget widget) throws Exception{
		if(widget.getName() == null ) {
			throw new Exception("Bk tidak boleh kosong");
		}
	}
	
}
