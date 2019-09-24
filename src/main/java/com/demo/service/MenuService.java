package com.demo.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.MenuDao;
import com.demo.exception.ValidationException;
import com.demo.model.Menu;

@Service
public class MenuService {

	@Autowired
	private MenuDao menuDao;
	
	public List<Menu> findAll(){
        return menuDao.findAll();
    }
	
	public List<Menu> findAll(Integer offset,Integer limit){
        return menuDao.findAll(offset, limit);
    }
	
	public Menu findById(String id){
        return menuDao.findOne(id);
    }
	
	public Menu findByBk(String code){
		Menu menu=new Menu();
		menu.setCode(code);
		return menuDao.findByBk(menu);
    }
	
	public void save(Menu menu) throws ValidationException {
		menu.setCreatedAt(new Timestamp(System.currentTimeMillis()));
    	valBkNotNull(menu);
		valBkNotExist(menu);
		valNonBk(menu);
		menuDao.create(menu);
	}
	
	public void update(Menu menu) throws ValidationException {
		menu.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
		valIdNotNull(menu);
		valIdExist(menu.getId());
		valBkNotNull(menu);
		valBkNotChange(menu);
		valNonBk(menu);
		valCreatedNotChange(menu);
		menuDao.update(menu);
	}
	
	public void delete(String id) throws ValidationException {
		valIdExist(id);
		menuDao.deleteById(id);
	}
	
	private void valIdExist(String id)throws ValidationException{
		if(!menuDao.isIdExist(id)) {
			throw new ValidationException("Data tidak ada");
		}
	}
	
	private void valIdNotNull(Menu menu)throws ValidationException {
		if(menu.getId().isEmpty()) {
			throw new ValidationException("Id tidak boleh kosong");
		}
	}
	
	private void valNonBk(Menu menu)throws ValidationException{
		List<String> listErr = new ArrayList<String>();

		if(menu.getModule().getId().isEmpty()) {
			listErr.add("Module tidak boleh kosong");
		}
		if(menu.getName().isEmpty()) {
			listErr.add("Name tidak boleh kosong");
		}
		if(menu.getSortOrder()==null) {
			listErr.add("Sort Order tidak boleh kosong");
		}
		if(menu.getLevel()==null) {
			listErr.add("Level tidak boleh kosong");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	private void valBkNotExist(Menu menu)throws ValidationException{
		if(menuDao.isBkExist(menu)) {
			throw new ValidationException("Data sudah ada");
		}
	}	
	
	private void valBkNotChange(Menu menu)throws ValidationException{
		Menu tempMenu=findById(menu.getId());

		if(!tempMenu.getCode().equals(menu.getCode())) {
			throw new ValidationException("BK tidak boleh berubah");
		}
	}
	
	private void valBkNotNull(Menu menu) throws ValidationException{
		if(menu.getCode().isEmpty() ) {
			throw new ValidationException("Bk tidak boleh kosong");
		}
	}
	
	private void valCreatedNotChange(Menu menu)throws ValidationException {
		Menu tempMenu=findById(menu.getId());
		
		if(!tempMenu.getCreatedAt().equals(menu.getCreatedAt()) || !tempMenu.getCreatedBy().equals(menu.getCreatedBy())) {
			throw new ValidationException("created tidak boleh berubah");
		}
	}
}
