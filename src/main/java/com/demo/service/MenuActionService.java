package com.demo.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.MenuActionDao;
import com.demo.exception.ValidationException;
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
	
	public void save(MenuAction menuAction) throws ValidationException {
		menuAction.setCreatedAt(new Timestamp(System.currentTimeMillis()));
    	valBkNotNull(menuAction);
		valBkNotExist(menuAction);
		valNonBk(menuAction);
		menuActionDao.create(menuAction);
	}
	
	public void update(MenuAction menuAction) throws ValidationException {
		menuAction.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
		valIdNotNull(menuAction);
		valIdExist(menuAction.getId());
		valBkNotNull(menuAction);
		valBkNotChange(menuAction);
		valNonBk(menuAction);
		valCreatedNotChange(menuAction);
		menuActionDao.update(menuAction);
	}
	
	public void delete(String id) throws ValidationException {
		valIdExist(id);
		menuActionDao.deleteById(id);
	}
	
	private void valIdExist(String id)throws ValidationException{
		if(!menuActionDao.isIdExist(id)) {
			throw new ValidationException("Data tidak ada");
		}
	}
	
	private void valIdNotNull(MenuAction menuAction)throws ValidationException {
		if(menuAction.getId().isEmpty()) {
			throw new ValidationException("Id tidak boleh kosong");
		}
	}
	
	private void valNonBk(MenuAction menuAction)throws ValidationException{
		List<String> listErr = new ArrayList<String>();
		
		if(menuAction.getMenu().getId().isEmpty()) {
			listErr.add("Menu tidak boleh kosong");
		}
		if(menuAction.getName().isEmpty()) {
			listErr.add("Nama tidak boleh kosong");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}

	}
	
	private void valBkNotExist(MenuAction menuAction)throws ValidationException{
		if(menuActionDao.isBkExist(menuAction)) {
			throw new ValidationException("Data sudah ada");
		}
	}	
	
	private void valBkNotChange(MenuAction menuAction)throws ValidationException{
		MenuAction tempMenuAction=findById(menuAction.getId());

		if(!tempMenuAction.getCode().equals(menuAction.getCode())) {
			throw new ValidationException("BK tidak boleh berubah");
		}
	}
	
	private void valBkNotNull(MenuAction menuAction) throws ValidationException{
		if(menuAction.getCode().isEmpty()) {
			throw new ValidationException("Bk tidak boleh kosong");
		}
	}
	
	private void valCreatedNotChange(MenuAction menuAction)throws ValidationException {
		MenuAction tempMenuAction=findById(menuAction.getId());
		
		if(!tempMenuAction.getCreatedAt().equals(menuAction.getCreatedAt()) || !tempMenuAction.getCreatedBy().equals(menuAction.getCreatedBy())) {
			throw new ValidationException("created tidak boleh berubah");
		}
	}
}
