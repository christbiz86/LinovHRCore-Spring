package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.Session;

@Repository
public class SessionDao extends AbstractJpaDao<Session>{
	
	public SessionDao() {
		setClazz(Session.class);
	}

	@SuppressWarnings("unchecked")
	public Session findByBk(String userId) {
		List<Session> list= super.entityManager.createQuery("FROM Session WHERE user.id=:userId ")
				.setParameter("userId", userId)
				.getResultList();
		
		if (list.size() == 0) {
			return new Session();
		}
		else {
			return (Session)list.get(0);
		}
	}
	
	public boolean isBkExist(Session session) {
		if(findByBk(session.getUser().getId()).getId() == null) {
			return false;
		} else {
			return true;
		}
	}
}
