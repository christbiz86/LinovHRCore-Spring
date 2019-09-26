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

    public final void setClazz(final Class<T> clazzToSet) {
        this.clazz = clazzToSet;
    }

    public T findOne(final String id) {
    	return entityManager.find(clazz, id);
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll() {
    	System.out.println("query");
        return entityManager.createQuery("from " + clazz.getName()).getResultList();
    }

    public void create(final T entity) {
    	try {
            int pointer = 0;
            BaseEntity base = (BaseEntity)entity;
            Field[] listField = entity.getClass().getFields();
            System.err.println(listField.length);
            for(Field updateField: listField) {
                if(updateField.getName().equals("createdAt")) {
                    Object o2 = updateField.get(entity);
                    updateField.set(base, new Timestamp(System.currentTimeMillis()));
                    System.err.println(o2);
                }else if(updateField.getName().equals("createdBy")) {
                    Object o3 = updateField.get(entity);
                    updateField.set(base, "kosong");
                    System.err.println(o3);
                }else if(updateField.getName().equals("version")) {
                    Object o6 = updateField.get(entity);
                    updateField.set(base, new Long(0));
                    System.err.println(o6);
                }
                pointer++;
            }
            entityManager.persist(entity);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
    }

    public T update(final T entity) {
    	try {
    		if(entity instanceof Object) {
    			int pointer = 0;
				BaseEntity base = (BaseEntity)entity;
    			Field[] listField = entity.getClass().getFields();
    			System.err.println(listField.length);
    			for(Field updateField: listField) {
    				if(updateField.getName().equals("updatedAt")) {
    					Object o2 = updateField.get(entity);
    					updateField.set(base, new Timestamp(System.currentTimeMillis()));
    					System.err.println(o2);
    				}else if(updateField.getName().equals("updatedBy")) {
    					Object o3 = updateField.get(entity);
    					updateField.set(base, "kosong");
    					System.err.println(o3);
    				}else if(updateField.getName().equals("version")) {
    					Object o6 = updateField.get(entity);
    					updateField.set(base, Long.parseLong(String.valueOf(o6)));
    					System.err.println(o6);
    				}
    				pointer++;
    			}
    		}
		} catch (Exception e) {
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