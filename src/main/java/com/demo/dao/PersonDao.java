package com.demo.dao;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.demo.model.Person;

@Repository
public class PersonDao extends AbstractJpaDao<Person>{
	
	public PersonDao() {
        setClazz(Person.class);
    }
	
	public Person findByBk(String tenantId, String firstName, String lastName, Date birthDate, String phone) {
		return (Person) super.entityManager.createQuery("FROM Person "
				+ "WHERE tenant.id=:tenantId "
				+ "AND firstName=:firstName "
				+ "AND lastName=:lastName "
				+ "AND birthDate=:birthDate "
				+ "AND phone=:phone ")
				.setParameter("tenantId", tenantId)
				.setParameter("firstName", firstName)
				.setParameter("lastName", lastName)
				.setParameter("birthDate", birthDate)
				.setParameter("phone", phone)
				.getSingleResult();
	}
	
	public boolean isBkExist(String tenantId, String firstName, String lastName, Date birthDate, String phone) {
		if(findByBk(tenantId, firstName, lastName, birthDate, phone).getId() == null) {
			return false;
		} else {
			return true;
		}
	}
}
