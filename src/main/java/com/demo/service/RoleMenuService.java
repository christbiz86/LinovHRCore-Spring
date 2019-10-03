package com.demo.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.RoleMenuDao;
import com.demo.model.Menu;
import com.demo.model.Role;
import com.demo.model.RoleMenu;
import com.demo.model.Tenant;

@Service
public class RoleMenuService {

	@Autowired
	private RoleMenuDao roleMenuDao;
	
	public List<RoleMenu> findAll(){
        return roleMenuDao.findAll();
    }
	
	public List<RoleMenu> findAll(Integer offset,Integer limit){
        return roleMenuDao.findAll(offset, limit);
    }
	
	public RoleMenu findById(String id){
        return roleMenuDao.findOne(id);
    }
	
	public RoleMenu findByBk(String tenantId,String roleId,String menuId){
		RoleMenu roleMenu=new RoleMenu();
		Tenant tenant=new Tenant();
		Role role=new Role();
		Menu menu=new Menu();
		tenant.setId(tenantId);
		role.setId(roleId);
		menu.setId(menuId);
		roleMenu.setTenant(tenant);
		roleMenu.setRole(role);
		roleMenu.setMenu(menu);
		return roleMenuDao.findByBk(roleMenu);
    }
	
	public void save(RoleMenu roleMenu) throws Exception {
		roleMenu.setCreatedAt(new Timestamp(System.currentTimeMillis()));
    	valBkNotNull(roleMenu);
		valBkNotExist(roleMenu);
		valNonBk(roleMenu);
		roleMenuDao.create(roleMenu);
	}
	
	public void update(RoleMenu roleMenu) throws Exception {
		roleMenu.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
		valIdNotNull(roleMenu);
		valIdExist(roleMenu.getId());
		valBkNotNull(roleMenu);
		valBkNotChange(roleMenu);
		valNonBk(roleMenu);
		valCreatedNotChange(roleMenu);
		roleMenuDao.update(roleMenu);
	}
	
	public void delete(String id) throws Exception {
		valIdExist(id);
		roleMenuDao.deleteById(id);
	}
	
	private void valIdExist(String id)throws Exception{
		if(!roleMenuDao.isIdExist(id)) {
			throw new Exception("Data tidak ada");
		}
	}
	
	private void valIdNotNull(RoleMenu roleMenu)throws Exception {
		if(roleMenu.getId().isEmpty()) {
			throw new Exception("Id tidak boleh kosong");
		}
	}
	
	private void valNonBk(RoleMenu roleMenu)throws Exception{
		
		if(roleMenu.getMenuAction().getId().isEmpty()) {
			throw new Exception("Menu Action tidak boleh kosong");
		}
		
		
	}
	
	private void valBkNotExist(RoleMenu roleMenu)throws Exception{
		if(roleMenuDao.isBkExist(roleMenu)) {
			throw new Exception("Data sudah ada");
		}
	}	
	
	private void valBkNotChange(RoleMenu roleMenu)throws Exception{
		RoleMenu tempRoleMenu=findById(roleMenu.getId());

		if(!tempRoleMenu.getTenant().getId().equals(roleMenu.getTenant().getId())) {
			throw new Exception("BK tidak boleh berubah");
		}
	}
	
	private void valBkNotNull(RoleMenu roleMenu) throws Exception{
		if(roleMenu.getTenant().getId().isEmpty()) {
			throw new Exception("Bk tidak boleh kosong");
		}
	}
	
	private void valCreatedNotChange(RoleMenu roleMenu)throws Exception {
		RoleMenu tempRoleMenu=findById(roleMenu.getId());
		
		if(!tempRoleMenu.getCreatedAt().equals(roleMenu.getCreatedAt()) || !tempRoleMenu.getCreatedBy().equals(roleMenu.getCreatedBy())) {
			throw new Exception("created tidak boleh berubah");
		}
	}
}
