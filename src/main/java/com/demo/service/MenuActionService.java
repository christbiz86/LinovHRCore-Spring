package com.demo.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.MenuActionDao;
import com.demo.model.MenuAction;

@Service
public class MenuActionService {

	@Autowired
	private MenuActionDao menuActionDao;
	
	public List<MenuAction> findAll(){
        return menuActionDao.findAll();
    }
	
	public List<MenuAction> findAll(Integer offset,Integer limit){
        return menuActionDao.findAll(offset, limit);
    }
	
	public MenuAction findById(String id){
        return menuActionDao.findOne(id);
    }
	
	public MenuAction findByBk(String code){
		MenuAction menuAction=new MenuAction();
		menuAction.setCode(code);
		return menuActionDao.findByBk(menuAction);
    }
	
	public void save(MenuAction menuAction) throws Exception {
		menuAction.setCreatedAt(new Timestamp(System.currentTimeMillis()));
    	valBkNotNull(menuAction);
		valBkNotExist(menuAction);
		valNonBk(menuAction);
		menuActionDao.create(menuAction);
	}
	
	public void update(MenuAction menuAction) throws Exception {
		menuAction.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
		valIdNotNull(menuAction);
		valIdExist(menuAction.getId());
		valBkNotNull(menuAction);
		valBkNotChange(menuAction);
		valNonBk(menuAction);
		valCreatedNotChange(menuAction);
		menuActionDao.update(menuAction);
	}
	
	public void delete(String id) throws Exception {
		valIdExist(id);
		menuActionDao.deleteById(id);
	}
	
	private void valIdExist(String id)throws Exception{
		if(!menuActionDao.isIdExist(id)) {
			throw new Exception("Data tidak ada");
		}
	}
	
	private void valIdNotNull(MenuAction menuAction)throws Exception {
		if(menuAction.getId().isEmpty()) {
			throw new Exception("Id tidak boleh kosong");
		}
	}
	
	private void valNonBk(MenuAction menuAction)throws Exception{
		
		if(menuAction.getMenu().getId().isEmpty()) {
			throw new Exception("Menu tidak boleh kosong");
		}
		if(menuAction.getName().isEmpty()) {
			throw new Exception("Nama tidak boleh kosong");
		}

	}
	
	private void valBkNotExist(MenuAction menuAction)throws Exception{
		if(menuActionDao.isBkExist(menuAction)) {
			throw new Exception("Data sudah ada");
		}
	}	
	
	private void valBkNotChange(MenuAction menuAction)throws Exception{
		MenuAction tempMenuAction=findById(menuAction.getId());

		if(!tempMenuAction.getCode().equals(menuAction.getCode())) {
			throw new Exception("BK tidak boleh berubah");
		}
	}
	
	private void valBkNotNull(MenuAction menuAction) throws Exception{
		if(menuAction.getCode().isEmpty()) {
			throw new Exception("Bk tidak boleh kosong");
		}
	}
	
	private void valCreatedNotChange(MenuAction menuAction)throws Exception {
		MenuAction tempMenuAction=findById(menuAction.getId());
		
		if(!tempMenuAction.getCreatedAt().equals(menuAction.getCreatedAt()) || !tempMenuAction.getCreatedBy().equals(menuAction.getCreatedBy())) {
			throw new Exception("created tidak boleh berubah");
		}
	}
}
