package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.Module;

@Repository
public class ModuleDao extends AbstractJpaDao<Module>{

	public ModuleDao() {
		setClazz(Module.class);
	}
	
	@SuppressWarnings("unchecked")
    public List<Module> findAll(Integer offset, Integer limit){
        return super.entityManager.createQuery("FROM Module")
                .setFirstResult(offset).setMaxResults(limit).getResultList();
    }
	
	@SuppressWarnings("unchecked")
    public Module findByBk(Module module){
		List<Module>list= super.entityManager.createQuery("FROM Module WHERE code=:code ")
                .setParameter("code", module.getCode())
                .getResultList();
        
        if (list.size() == 0) {
			return new Module();
		}
		else {
			return (Module)list.get(0);
		}
    }
	
	public boolean isBkExist(Module module) {
		
		if(findByBk(module).getId()==null) {
			return false;
		}else {
			return true;
		}	 
	}
}
