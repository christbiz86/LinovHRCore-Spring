package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.UserRoleWidget;

@Repository
public class UserRoleWidgetDao  extends AbstractJpaDao<UserRoleWidget>{

	public UserRoleWidgetDao() {
		setClazz(UserRoleWidget.class);
	}
	
	@SuppressWarnings("unchecked")
    public List<UserRoleWidget> findAll(Integer offset, Integer limit){
        return super.entityManager.createQuery("FROM UserRoleWidget")
                .setFirstResult(offset).setMaxResults(limit).getResultList();
    }
	
	@SuppressWarnings("unchecked")
    public UserRoleWidget findByBk(UserRoleWidget userRoleWidget){
		List<UserRoleWidget>list= super.entityManager.createQuery("FROM UserRoleWidget WHERE userRole.id=:userRoleId AND widget.id=:widgetId ")
                .setParameter("userRoleId", userRoleWidget.getUserRole().getId())
                .setParameter("widgetId", userRoleWidget.getWidget().getId())
                .getResultList();
        
        if (list.size() == 0) {
			return new UserRoleWidget();
		}
		else {
			return (UserRoleWidget)list.get(0);
		}
    }
	
	public boolean isBkExist(UserRoleWidget userRoleWidget) {
		
		if(findByBk(userRoleWidget).getId()==null) {
			return false;
		}else {
			return true;
		}	 
	}
}
