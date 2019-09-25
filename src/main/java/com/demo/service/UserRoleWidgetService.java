package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.UserRoleWidgetDao;
import com.demo.exception.ValidationException;
import com.demo.model.UserRole;
import com.demo.model.UserRoleWidget;
import com.demo.model.Widget;

@Service
public class UserRoleWidgetService {

	@Autowired
	private UserRoleWidgetDao userRoleWidgetDao;
	
	public List<UserRoleWidget> findAll(){
        return userRoleWidgetDao.findAll();
    }
	
	public List<UserRoleWidget> findAll(Integer offset,Integer limit){
        return userRoleWidgetDao.findAll(offset, limit);
    }
	
	public UserRoleWidget findById(String id){
        return userRoleWidgetDao.findOne(id);
    }
	
	public UserRoleWidget findByBk(String userRoleId,String widgetId){
		UserRoleWidget userRoleWidget=new UserRoleWidget();
		UserRole userRole=new UserRole();
		Widget widget=new Widget();
		userRole.setId(userRoleId);
		widget.setId(widgetId);
		userRoleWidget.setUserRole(userRole);
		userRoleWidget.setWidget(widget);
		return userRoleWidgetDao.findByBk(userRoleWidget);
    }
	
	public void save(UserRoleWidget userRoleWidget) throws ValidationException {
		valBkNotNull(userRoleWidget);
		valBkNotExist(userRoleWidget);
		valNonBk(userRoleWidget);
		userRoleWidgetDao.create(userRoleWidget);
	}
	
	public void update(UserRoleWidget userRoleWidget) throws ValidationException {
		valIdNotNull(userRoleWidget);
		valIdExist(userRoleWidget.getId());
		valBkNotNull(userRoleWidget);
		valBkNotChange(userRoleWidget);
		userRoleWidgetDao.update(userRoleWidget);
	}
	
	public void delete(String id) throws ValidationException {
		valIdExist(id);
		userRoleWidgetDao.deleteById(id);
	}
	
	private void valIdExist(String id)throws ValidationException{
		if(!userRoleWidgetDao.isIdExist(id)) {
			throw new ValidationException("Data tidak ada");
		}
	}
	
	private void valIdNotNull(UserRoleWidget userRoleWidget)throws ValidationException {
		if(userRoleWidget.getId().isEmpty()) {
			throw new ValidationException("Id tidak boleh kosong");
		}
	}
	
	private void valNonBk(UserRoleWidget userRoleWidget)throws ValidationException{

	}
	
	private void valBkNotExist(UserRoleWidget userRoleWidget)throws ValidationException{
		if(userRoleWidgetDao.isBkExist(userRoleWidget)) {
			throw new ValidationException("Data sudah ada");
		}
	}	
	
	private void valBkNotChange(UserRoleWidget userRoleWidget)throws ValidationException{
		UserRoleWidget tempUserRoleWidget=findById(userRoleWidget.getId());

		if(!tempUserRoleWidget.getUserRole().getId().equals(userRoleWidget.getUserRole().getId()) || !tempUserRoleWidget.getWidget().getId().equals(userRoleWidget.getWidget().getId())) {
			throw new ValidationException("BK tidak boleh berubah");
		}
	}
	
	private void valBkNotNull(UserRoleWidget userRoleWidget) throws ValidationException{
		if(userRoleWidget.getUserRole().getId().isEmpty() || userRoleWidget.getWidget().getId().isEmpty()) {
			throw new ValidationException("Bk tidak boleh kosong");
		}
	}
	
}
