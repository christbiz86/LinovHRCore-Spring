package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.Assignment;

@Repository
public class AssignmentDao extends AbstractJpaDao<Assignment>{

	public AssignmentDao() {
		setClazz(Assignment.class);
	}

	@SuppressWarnings("unchecked")
    public List<Assignment> findAll(Integer offset, Integer limit){
        return super.entityManager.createQuery("FROM Assignment")
                .setFirstResult(offset).setMaxResults(limit).getResultList();
    }

	@SuppressWarnings("unchecked")
	public Assignment findByBk(String companyId,String positionSlotId) {
		List<Assignment> list= super.entityManager.createQuery("FROM Assignment WHERE company.id=:companyId AND positionSlot.id=:positionSlotId ")
				.setParameter("companyId", companyId)
				.setParameter("positionSlotId", positionSlotId)
				.getResultList();
		
		if (list.size() == 0) {
			return new Assignment();
		}
		else {
			return (Assignment)list.get(0);
		}
	}
	
	public boolean isBkExist(Assignment assignment) {
		if(findByBk(assignment.getCompany().getId(),assignment.getPositionSlot().getId()).getId() == null) {
			return false;
		} else {
			return true;
		}
	}
}
