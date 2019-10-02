package com.demo.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.demo.model.AssignmentStatus;

public class AssignmentStatusDao extends AbstractJpaDao<AssignmentStatus> {
	
	public AssignmentStatusDao() {
        setClazz(AssignmentStatus.class);
    }
	
	@SuppressWarnings("unchecked")
    @Transactional
	public AssignmentStatus findByBk(String assignment, String lovAsta) {
		List<AssignmentStatus> list = super.entityManager
                .createQuery("FROM AssignmentStatus WHERE assignment.id = :assignment AND lovAsta = :lovAsta")
                .setParameter("assignment", assignment)
                .setParameter("lovAsta", lovAsta)
                .getResultList();

        if (list.size() == 0) {
            return new AssignmentStatus();
        }
        else {
            return list.get(0);
        }
    }
	
	public boolean isBkExist(String assignment, String lovAsta) {
        if(findByBk(assignment, lovAsta).getId() == null) {
            return false;
        }else {
            return true;
        }
    }

}
