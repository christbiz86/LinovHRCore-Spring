package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.RoleMenu;

@Repository
public class RoleMenuDao extends AbstractJpaDao<RoleMenu>{
	
	public RoleMenuDao() {
		setClazz(RoleMenu.class);
	}

	@SuppressWarnings("unchecked")
    public List<RoleMenu> findAll(Integer offset, Integer limit){
        return super.entityManager.createQuery("FROM RoleMenu")
                .setFirstResult(offset).setMaxResults(limit).getResultList();
    }
	
	@SuppressWarnings("unchecked")
    public RoleMenu findByBk(RoleMenu roleMenu){
		List<RoleMenu>list= super.entityManager.createQuery("FROM RoleMenu WHERE tenant.id=:tenantId AND role.id=:roleId AND menu.id=:menuId ")
                .setParameter("tenantId", roleMenu.getTenant().getId())
                .setParameter("roleId", roleMenu.getRole().getId())
                .setParameter("menuId", roleMenu.getMenu().getId())
                .getResultList();
        
        if (list.size() == 0) {
			return new RoleMenu();
		}
		else {
			return (RoleMenu)list.get(0);
		}
    }
	
	public boolean isBkExist(RoleMenu roleMenu) {
		
		if(findByBk(roleMenu).getId()==null) {
			return false;
		}else {
			return true;
		}	 
	}
}
