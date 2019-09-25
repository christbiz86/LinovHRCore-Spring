package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.WidgetDao;
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
		if(widget.getDescription().isEmpty()) {
			throw new Exception("Description tidak boleh kosong");
		}
		if(widget.getAppCode().isEmpty()) {
			throw new Exception("Appcode tidak boleh kosong");
		}
		if(widget.getWidgetType().getId().isEmpty()) {
			throw new Exception("widget type id tidak boleh kosong");
		}
		if(widget.getParamIn().isEmpty()) {
			throw new Exception("Param In tidak boleh kosong");
		}
		if(widget.getParamOut().isEmpty()) {
			throw new Exception("Param Out tidak boleh kosong");
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
		if(widget.getName().isEmpty() ) {
			throw new Exception("Bk tidak boleh kosong");
		}
	}
	
}
