package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.PersonAddress;

@Repository
public class PersonAddressDao extends ParentDao{

	@SuppressWarnings("unchecked")
    public List<PersonAddress> findAll(Integer offset, Integer limit){
        return super.entityManager.createQuery("FROM PersonAddress")
                .setFirstResult(offset).setMaxResults(limit).getResultList();
    }
	
	@SuppressWarnings("unchecked")
    public PersonAddress findById(String id){
		
		List<PersonAddress> list= super.entityManager.createQuery("FROM PersonAddress where id =:id ")
        		.setParameter("id", id)
                .getResultList();
		
 		if (list.size() == 0) {
			return new PersonAddress();
		}
		else {
			return (PersonAddress)list.get(0);
		}
    }
	
	@SuppressWarnings("unchecked")
    public PersonAddress findByBk(PersonAddress personAddress){
		
		List<PersonAddress> list= super.entityManager.createQuery("FROM PersonAddress WHERE person.id =:personId AND address=:address ")
        		.setParameter("personId", personAddress.getPerson().getId())
        		.setParameter("address", personAddress.getAddress())
                .getResultList();
		
 		if (list.size() == 0) {
			return new PersonAddress();
		}
		else {
			return (PersonAddress)list.get(0);
		}
    }
	
	public void save(PersonAddress personAddress) {
		entityManager.merge(personAddress);
	}
	
	public void delete(PersonAddress personAddress) {
		entityManager.remove(personAddress);
	}
	
	public boolean isExist(String id) {
		if (findById(id).getId()==null) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean isBkExist(PersonAddress personAddress) {
		
		if(findByBk(personAddress).getId()==null) {
			return false;
		}else {
			return true;
		}	 
	}

	@SuppressWarnings("unchecked")
	public List<PersonAddress> findByPersonId(String personId) {
		return  super.entityManager.createQuery("FROM PersonAddress where person.id =:personId ")
        		.setParameter("personId", personId)
                .getResultList();
	}
}
