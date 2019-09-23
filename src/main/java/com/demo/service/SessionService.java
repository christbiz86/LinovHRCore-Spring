package com.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.SessionDao;
import com.demo.exception.ValidationException;
import com.demo.model.Session;

@Service
public class SessionService {
	
	@Autowired
	private SessionDao sessionDao;
	
	public List<Session> findAll() throws Exception{
        return sessionDao.findAll();
    }
	
	public Session findById(String id) throws Exception{
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
		if(session.getId()==null) {
			throw new Exception("Id tidak boleh kosong");
		}
	}
	
	private void valBkNotNull(Session session) throws Exception {
		List<String> listErr = new ArrayList<String>();
		if(session.getUser().getId()==null) {
			listErr.add("User tidak boleh kosong");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	private void valBkNotExist(Session session)throws Exception{
		if(sessionDao.isBkExist(session)) {
			throw new Exception("Data sudah ada");
		}
	}	

	private void valNonBk(Session session) throws Exception {
		List<String> listErr = new ArrayList<String>();
		if(session.getTenant().getId()==null) {
			listErr.add("Tenant tidak boleh kosong");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}	

	private void valBkNotChange(Session session)throws Exception{
		Session tempSession=findById(session.getId());

		if(!tempSession.getUser().getId().equals(session.getUser().getId()) ) {
			throw new Exception("BK tidak boleh berubah");
		}
	}
}
