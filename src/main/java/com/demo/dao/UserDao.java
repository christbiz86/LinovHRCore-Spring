package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.User;

@Repository
public class UserDao extends ParentDao{

	@SuppressWarnings("unchecked")
    public List<User> findAll(Integer offset, Integer limit){
        return super.entityManager.createQuery("FROM User")
                .setFirstResult(offset).setMaxResults(limit).getResultList();
    }
	
	@SuppressWarnings("unchecked")
    public User findById(String id){
		List<User>list= super.entityManager.createQuery("FROM User where id=:id")
                .setParameter("id", id)
                .getResultList();
        
        if (list.size() == 0) {
			return new User();
		}
		else {
			return (User)list.get(0);
		}
    }
	
	@SuppressWarnings("unchecked")
    public User findByBk(User user){
		List<User>list= super.entityManager.createQuery("FROM User WHERE tenant.id=:tenantId AND username=:username ")
                .setParameter("tenantId", user.getTenant().getId())
                .setParameter("username", user.getUsername())
                .getResultList();
        
        if (list.size() == 0) {
			return new User();
		}
		else {
			return (User)list.get(0);
		}
    }

	public void save(User user) {
		entityManager.merge(user);
	}
	
	public void delete(User user) {
		entityManager.remove(user);
	}
	
	public boolean isExist(String id) {
		if (findById(id).getId() == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean isBkExist(User user) {
		
		if(findByBk(user).getId()==null) {
			return false;
		}else {
			return true;
		}	 
	}
	
}