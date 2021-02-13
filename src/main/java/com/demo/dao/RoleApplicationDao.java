package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.RoleApplication;

@Repository
public class RoleApplicationDao extends AbstractJpaDao<RoleApplication>{

	public RoleApplicationDao() {
		setClazz(RoleApplication.class);
	}
	
	@SuppressWarnings("unchecked")
    public List<RoleApplication> findAll(Integer offset, Integer limit){
        return super.entityManager.createQuery("FROM RoleApplication")
                .setFirstResult(offset).setMaxResults(limit).getResultList();
    }
		
	@SuppressWarnings("unchecked")
    public RoleApplication findByBk(RoleApplication roleApplication){
		
		List<RoleApplication> list= super.entityManager.createQuery("FROM RoleApplication WHERE role.id =:roleId AND application.id=:applicationId ")
        		.setParameter("roleId", roleApplication.getRole().getId())
        		.setParameter("applicationId", roleApplication.getApplication().getId())
                .getResultList();
		
 		if (list.size() == 0) {
			return new RoleApplication();
		}
		else {
			return (RoleApplication)list.get(0);
		}
    }
		
	public boolean isBkExist(RoleApplication roleApplication) {
		
		if(findByBk(roleApplication).getId()==null) {
			return false;
		}else {
			return true;
		}	 
	}

}
