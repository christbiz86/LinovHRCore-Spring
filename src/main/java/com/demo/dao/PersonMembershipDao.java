package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.PersonMembership;

@Repository
public class PersonMembershipDao extends AbstractJpaDao<PersonMembership>{
	public PersonMembershipDao() {
		setClazz(PersonMembership.class);
	}
	
	@SuppressWarnings("unchecked")
	public PersonMembership findByBk(String personId, String lovId) {
		List<PersonMembership> list = super.entityManager.createQuery("FROM PersonMembership WHERE person.id=:personId "
				+ "AND lovMemberType.id=:lovId").setParameter("personId", personId)
				.setParameter("lovId", lovId).getResultList();
		
		if(list.size() == 0) {
			return new PersonMembership();
		} else {
			return list.get(0);
		}
	}
	
	public boolean isBkExist(String personId, String lovId) {
		if(findByBk(personId, lovId).getId() == null) {
			return false;
		} else {
			return true;
		}
	}
}
