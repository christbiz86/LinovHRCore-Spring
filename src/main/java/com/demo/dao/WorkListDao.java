package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.WorkList;

@Repository
public class WorkListDao extends AbstractJpaDao<WorkList>{

	public WorkListDao() {
		setClazz(WorkList.class);
	}
	
	@SuppressWarnings("unchecked")
    public List<WorkList> findAll(Integer offset, Integer limit){
        return super.entityManager.createQuery("FROM WorkList")
                .setFirstResult(offset).setMaxResults(limit).getResultList();
    }
	
//	@SuppressWarnings("unchecked")
//    public WorkList findByBk(WorkList workList){
//		List<WorkList>list= super.entityManager.createQuery("FROM WorkList WHERE =: AND =: ")
//                .setParameter("", )
//                .setParameter("", )
//                .getResultList();
//        
//        if (list.size() == 0) {
//			return new WorkList();
//		}
//		else {
//			return (WorkList)list.get(0);
//		}
//    }
	
//	public boolean isBkExist(WorkList workList) {
//		
//		if(findByBk(workList).getId()==null) {
//			return false;
//		}else {
//			return true;
//		}	 
//	}
}
