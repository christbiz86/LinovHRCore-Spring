package com.demo.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.MenuDao;
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
	
	public void save(Menu menu) throws Exception {
		menu.setCreatedAt(new Timestamp(System.currentTimeMillis()));
    	valBkNotNull(menu);
		valBkNotExist(menu);
		valNonBk(menu);
		menuDao.create(menu);
	}
	
	public void update(Menu menu) throws Exception {
		menu.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
		valIdNotNull(menu);
		valIdExist(menu.getId());
		valBkNotNull(menu);
		valBkNotChange(menu);
		valNonBk(menu);
		valCreatedNotChange(menu);
		menuDao.update(menu);
	}
	
	public void delete(String id) throws Exception {
		valIdExist(id);
		menuDao.deleteById(id);
	}
	
	private void valIdExist(String id)throws Exception{
		if(!menuDao.isIdExist(id)) {
			throw new Exception("Data tidak ada");
		}
	}
	
	private void valIdNotNull(Menu menu)throws Exception {
		if(menu.getId().isEmpty()) {
			throw new Exception("Id tidak boleh kosong");
		}
	}
	
	private void valNonBk(Menu menu)throws Exception{

		if(menu.getModule().getId().isEmpty()) {
			throw new Exception("Module tidak boleh kosong");
		}
		if(menu.getName().isEmpty()) {
			throw new Exception("Name tidak boleh kosong");
		}
		if(menu.getSortOrder()==null) {
			throw new Exception("Sort Order tidak boleh kosong");
		}
		if(menu.getLevel()==null) {
			throw new Exception("Level tidak boleh kosong");
		}
		
	}
	
	private void valBkNotExist(Menu menu)throws Exception{
		if(menuDao.isBkExist(menu)) {
			throw new Exception("Data sudah ada");
		}
	}	
	
	private void valBkNotChange(Menu menu)throws Exception{
		Menu tempMenu=findById(menu.getId());

		if(!tempMenu.getCode().equals(menu.getCode())) {
			throw new Exception("BK tidak boleh berubah");
		}
	}
	
	private void valBkNotNull(Menu menu) throws Exception{
		if(menu.getCode().isEmpty() ) {
			throw new Exception("Bk tidak boleh kosong");
		}
	}
	
	private void valCreatedNotChange(Menu menu)throws Exception {
		Menu tempMenu=findById(menu.getId());
		
		if(!tempMenu.getCreatedAt().equals(menu.getCreatedAt()) || !tempMenu.getCreatedBy().equals(menu.getCreatedBy())) {
			throw new Exception("created tidak boleh berubah");
		}
	}
}
