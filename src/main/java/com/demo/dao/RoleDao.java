package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.Role;

@Repository
public class RoleDao extends AbstractJpaDao<Role>{
	
	public RoleDao() {
		setClazz(Role.class);
	}

	@SuppressWarnings("unchecked")
    public List<Role> findAll(Integer offset, Integer limit){
        return super.entityManager.createQuery("FROM Role")
                .setFirstResult(offset).setMaxResults(limit).getResultList();
    }

	@SuppressWarnings("unchecked")
	public Role findByBk(String companyId,String name) {
		List<Role> list= super.entityManager.createQuery("FROM Role WHERE company.id=:companyId AND name=:name ")
				.setParameter("companyId", companyId)
				.setParameter("name", name)
				.getResultList();
		
		if (list.size() == 0) {
			return new Role();
		}
		else {
			return (Role)list.get(0);
		}
	}
	
	public boolean isBkExist(Role role) {
		if(findByBk(role.getCompany().getId(),role.getName()).getId() == null) {
			return false;
		} else {
			return true;
		}
	}
}
