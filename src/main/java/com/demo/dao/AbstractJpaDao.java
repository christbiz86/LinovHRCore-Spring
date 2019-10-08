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
        return entityManager.createQuery("from " + clazz.getName()).getResultList();
    }

    public void create(final T entity) throws IllegalArgumentException, IllegalAccessException {
    	Field[] listField = entity.getClass().getSuperclass().getDeclaredFields();
    	for (Field field : listField) {
			field.setAccessible(true);
			if(field.getName().equals("createdAt")) {
            	field.set(entity, new Timestamp(System.currentTimeMillis()));
            }else if(field.getName().equals("createdBy")) {
            	field.set(entity, "kosong");
            }else if(field.getName().equals("version")) {
                field.set(entity, 0L);
            }
		}
	}

	public T update(final T entity) {
		try {
			if (entity instanceof Object) {
				int pointer = 0;
				BaseEntity base = (BaseEntity) entity;
				Field[] listField = entity.getClass().getFields();
				System.err.println(listField.length);
				for (Field updateField : listField) {
					if (updateField.getName().equals("updatedAt")) {
						Object o2 = updateField.get(entity);
						updateField.set(base, new Timestamp(System.currentTimeMillis()));
						System.err.println(o2);
					} else if (updateField.getName().equals("updatedBy")) {
						Object o3 = updateField.get(entity);
						updateField.set(base, "kosong");
						System.err.println(o3);
					} else if (updateField.getName().equals("version")) {
						Object o6 = updateField.get(entity);
						updateField.set(base, Long.parseLong(String.valueOf(o6)) + 1);
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
		if (findOne(entityId) == null) {
			return false;
		} else {
			return true;
		}
	}
    
    private void valVersion(final String entityId, Long versionUp) throws Exception {
    	BaseEntity base = (BaseEntity) findOne(entityId);
    	if(base.getVersion() != versionUp) {
    		throw new Exception("Version Not Match");
    	}
    }
    
    public void versionUp(final T entity) throws Exception {
    	Field field = entity.getClass().getSuperclass().getDeclaredField("id");
    	field.setAccessible(true);
    	T originalEntity = findOne((String) field.get(entity));
    	
    	field = originalEntity.getClass().getSuperclass().getDeclaredField("version");
    	field.setAccessible(true);
    	Long version = (Long) field.get(originalEntity);
    	version++;
    	field.set(originalEntity, version);
    	entityManager.merge(originalEntity);
    }

}