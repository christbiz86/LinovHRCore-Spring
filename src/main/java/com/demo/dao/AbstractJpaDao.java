package com.demo.dao;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.Timestamp;
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
    				BaseEntity insertBase = (BaseEntity)entity;
    				insertBase.setVersion(new Long(0));
    				insertBase.setCreatedBy("kosong");
    				insertBase.setCreatedAt(new Timestamp(System.currentTimeMillis()));
    				entityManager.persist(entity);
    			}
    			
    			
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
        
    }

    public T update(final T entity) {
    	T o = null;
    	try {
    		if(entity instanceof Object) {
//    			System.err.println(entity.getClass().getField("updatedBy"));
//        		if(entity.getClass().getField("updatedBy").equals(null)) {
//        		Field setUpdatedBy = entity.getClass().getField("updatedBy");
//        		setUpdatedBy.set(entity, "kosong");
//        		}else if(entity.getClass().getField("updatedAt").equals(null)) {
//        			Field setUpdatedAt = entity.getClass().getField("updatedAt");
//            		setUpdatedAt.set(entity,new Timestamp(System.currentTimeMillis()));
//        		}else if(entity.getClass().getField("version").equals(null)) {
//        			Field setVersion = entity.getClass().getField("version");
//            		setVersion.set(entity,setVersion.getLong("version")+1);
//        		}
        		o = entityManager.merge(entity);
    		}
    		
    		
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
    	
        return o;
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