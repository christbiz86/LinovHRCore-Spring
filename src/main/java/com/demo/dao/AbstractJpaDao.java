
package com.demo.dao;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class AbstractJpaDao<T extends Serializable> {

    private Class<T> clazz;
    private T data;
    
    @Value("spring.datasource.url")
	private String datasourceUrl;
	
	@Value("spring.datasource.username")
	private String datasourceUsername;
	
	@Value("spring.datasource.password")
	private String datasourcePassword;
	
	@Value("spring.jpa.properties.hibernate.default_schema")
	private String defaultSchema;

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

	public void create(final T entity) throws Exception {
		try {
			Field[] listField = entity.getClass().getSuperclass().getDeclaredFields();
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String username = String.valueOf(auth.getPrincipal());
			for (Field field : listField) {
				field.setAccessible(true);
				if (field.getName().equals("createdAt")) {
					field.set(entity, new Timestamp(System.currentTimeMillis()));
				} else if (field.getName().equals("createdBy")) {
					field.set(entity, username);
				} else if (field.getName().equals("version")) {
					field.set(entity, 0L);
				} else if (field.getName().equals("isActive")) {
					field.set(entity, true);
				}
			}
			entityManager.persist(entity);

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public T update(final T entity) throws Exception {
		try {
			Field id = entity.getClass().getSuperclass().getDeclaredField("id");
			id.setAccessible(true);
			data = findOne(id.get(entity).toString());
			valVersion(entity, data);
			Field[] listField = entity.getClass().getSuperclass().getDeclaredFields();
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String username = String.valueOf(auth.getPrincipal());
			for (Field updateField : listField) {
				updateField.setAccessible(true);																																			    													
				if (updateField.getName().equals("updatedAt")) {
					updateField.set(entity, new Timestamp(System.currentTimeMillis()));
				} else if (updateField.getName().equals("updatedBy")) {
					updateField.set(entity, username);
				} else if (updateField.getName().equals("version")) {
					Object o6 = updateField.get(data);																																																														
					updateField.set(entity, Long.parseLong(String.valueOf(o6)) + 1);
				} else if (updateField.getName().equals("createdAt")) {
					Field createdAt = data.getClass().getSuperclass().getDeclaredField("createdAt");
					createdAt.setAccessible(true);
					updateField.set(entity, createdAt.get(data));
				} else if (updateField.getName().equals("createdBy")) {
					Field createdBy = data.getClass().getSuperclass().getDeclaredField("createdBy");
					createdBy.setAccessible(true);
					updateField.set(entity, createdBy.get(data));
				} else if (updateField.getName().equals("isActive")) {
					Field isActive = data.getClass().getSuperclass().getDeclaredField("isActive");
					isActive.setAccessible(true);
					updateField.set(entity, isActive.get(data));
				}
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
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
    
    private void valVersion(T updatedEntity, T storedEntity) throws Exception {
    	Field upedEntityVer = updatedEntity.getClass().getSuperclass().getDeclaredField("version");
    	upedEntityVer.setAccessible(true);
    	Field storEntityVer = storedEntity.getClass().getSuperclass().getDeclaredField("version");
    	storEntityVer.setAccessible(true);
    	if(!upedEntityVer.get(updatedEntity).equals(storEntityVer.get(storedEntity))) {
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