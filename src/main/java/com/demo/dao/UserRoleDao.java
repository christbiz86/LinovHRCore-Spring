package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.UserRole;

@Repository
public class UserRoleDao extends AbstractJpaDao<UserRole>{

	public UserRoleDao() {
		setClazz(UserRole.class);
	}
	
	@SuppressWarnings("unchecked")
    public List<UserRole> findAll(Integer offset, Integer limit){
        return super.entityManager.createQuery("FROM UserRole")
                .setFirstResult(offset).setMaxResults(limit).getResultList();
    }
	
	@SuppressWarnings("unchecked")
    public UserRole findByBk(UserRole userRole){
		List<UserRole>list= super.entityManager.createQuery("FROM UserRole WHERE user.id=:userId AND role.id=:roleId ")
                .setParameter("userId", userRole.getUser().getId())
                .setParameter("roleId", userRole.getRole().getId())
                .getResultList();
        
        if (list.size() == 0) {
			return new UserRole();
		}
		else {
			return (UserRole)list.get(0);
		}
    }
	
	public boolean isBkExist(UserRole userRole) {
		
		if(findByBk(userRole).getId()==null) {
			return false;
		}else {
			return true;
		}	 
	}
}
