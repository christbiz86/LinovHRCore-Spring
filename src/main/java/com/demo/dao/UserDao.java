package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.User;

@Repository
public class UserDao extends AbstractJpaDao<User>{

	public UserDao() {
        setClazz(User.class);
    }
	
	@SuppressWarnings("unchecked")
    public List<User> findAll(Integer offset, Integer limit){
        return super.entityManager.createQuery("FROM User")
                .setFirstResult(offset).setMaxResults(limit).getResultList();
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
	
	public boolean isBkExist(User user) {
		
		if(findByBk(user).getId()==null) {
			return false;
		}else {
			return true;
		}	 
	}
	
}