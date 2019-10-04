package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.model.PersonFamily;

@Repository
public class PersonFamilyDao extends AbstractJpaDao<PersonFamily> {
	
	public PersonFamilyDao() {
        setClazz(PersonFamily.class);
    }

    @SuppressWarnings("unchecked")
    @Transactional
	public PersonFamily findByBk(String person, String lovFamr, String name) {
		List<PersonFamily> list = super.entityManager
                .createQuery("FROM PersonFamily"
                		+ " WHERE person.id = :person AND lovFamr.id = :lovFamr AND name = :name")
                .setParameter("person", person)
                .setParameter("lovFamr", lovFamr)
                .setParameter("name", name)
                .getResultList();

        if (list.size() == 0) {
            return new PersonFamily();
        }
        else {
            return list.get(0);
        }
    }

    public boolean isBkExist(String person, String lovFamr, String name) {
        if(findByBk(person, lovFamr, name).getId() == null) {
            return false;
        }else {
            return true;
        }
    }

}
