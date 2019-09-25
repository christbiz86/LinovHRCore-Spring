package com.demo.dao;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.demo.model.BaseEntity;

public abstract class AbstractJpaDao<T extends Serializable> {

    private Class<T> clazz;

    @PersistenceContext
    protected EntityManager entityManager;
    
    private String entityId;

    public final void setClazz(final Class<T> clazzToSet) {
        this.clazz = clazzToSet;
    }

    public T findOne(final String id) {
    	entityId = id;
        return entityManager.find(clazz, id);
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return entityManager.createQuery("from " + clazz.getName()).getResultList();
    }

    public void create(final T entity) {
    	try {
    		
    			if(entity instanceof Object) {
    				BaseEntity base = (BaseEntity)entity;
    				base.setVersion(new Long(0));
    				base.setCreatedBy("kosong");
    				base.setCreatedAt(new Timestamp(System.currentTimeMillis()));
    				entityManager.persist(entity);
    				
    			}
    			
    			
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
        
    }

    public T update(final T entity) {
    	try {
    		System.err.println(entity.toString());
    		if(entity instanceof Object) {
    			
    			Field[] listField = BaseEntity.class.getDeclaredFields();
    			int pointer=0;
    			for(Field f: listField) {
    				System.out.println(pointer);
    				if(f.get("updated_at").equals(null)) {
    					f.set("updated_at", new Timestamp(System.currentTimeMillis()));
    				}else if(f.get("updated_by").equals(null)) {
    					f.set("updated_by", "kosong");
    				}else if(!f.get("version").equals(null)) {
    					f.set("version",f.getLong("version")+1);
    				}
    				pointer++;
    			}
    			
    		} 
    		
    		
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
    	
        return entityManager.merge(entity);
    }

    public void delete(final T entity) {
        entityManager.remove(entity);
    }

    public void deleteById(final String entityId) {
        final T entity = findOne(entityId);
        delete(entity);
    }

    public boolean isIdExist(final String entityId) {
        if(findOne(entityId) == null) {
            return false;
        } else {
            return true;
        }
    }

}