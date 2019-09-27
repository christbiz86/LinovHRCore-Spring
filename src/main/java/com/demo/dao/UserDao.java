package com.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.demo.helper.Encryption;
import com.demo.model.User;

@Repository
public class UserDao extends AbstractJpaDao<User>{

	@Autowired
	private Encryption encryption;
	
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


	public boolean checkAuthentication(String username, String password) {
		Short count = (Short)this.entityManager.createNativeQuery(
"SELECT COUNT(*)::::SMALLINT FROM users WHERE username = :username AND password = :password"
		)
		.setParameter("username", username)
		.setParameter("password", encryption.encrypt(password))
		.getSingleResult();

		if(count == 1) {
			return true;					
		}
		else {
			return false;
		}
		
	}
	
}