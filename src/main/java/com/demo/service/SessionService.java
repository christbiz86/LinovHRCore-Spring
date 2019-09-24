package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.SessionDao;
import com.demo.model.Session;

@Service
public class SessionService {
	
	@Autowired
	private SessionDao sessionDao;
	
	public List<Session> findAll(){
        return sessionDao.findAll();
    }
	
	public Session findById(String id){
		if(sessionDao.isIdExist(id)) {
			return sessionDao.findOne(id);
		}else {
			return new Session();
		}
    }
	
	public void save(Session session) throws Exception{
		valBkNotNull(session);
		valBkNotExist(session);
		valNonBk(session);
		sessionDao.create(session);
	}
	
	public void update(Session session) throws Exception {
		valIdNotNull(session);
		valIdExist(session.getId());
		valBkNotNull(session);
		valBkNotChange(session);
		valNonBk(session);
		sessionDao.update(session);
	}
	
	public void delete(String id) throws Exception {
		valIdExist(id);
		sessionDao.deleteById(id);
	}
	
	private void valIdExist(String id)throws Exception{
		if(!sessionDao.isIdExist(id)) {
			throw new Exception("Data tidak ada");
		}
	}
	
	private void valIdNotNull(Session session)throws Exception {
		if(session.getId().isEmpty()) {
			throw new Exception("Id tidak boleh kosong");
		}
	}
	
	private void valBkNotNull(Session session) throws Exception {
		if(session.getUser().getId().isEmpty()) {
			throw new Exception("User tidak boleh kosong");
		}
	}
	
	private void valBkNotExist(Session session)throws Exception{
		if(sessionDao.isBkExist(session)) {
			throw new Exception("Data sudah ada");
		}
	}	

	private void valNonBk(Session session) throws Exception {
		if(session.getTenant().getId().isEmpty()) {
			throw new Exception("Tenant tidak boleh kosong");
		}
	}	

	private void valBkNotChange(Session session)throws Exception{
		Session tempSession=findById(session.getId());

		if(!tempSession.getUser().getId().equals(session.getUser().getId()) ) {
			throw new Exception("BK tidak boleh berubah");
		}
	}
}
