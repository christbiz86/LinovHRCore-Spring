package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.WorkListDao;
import com.demo.model.WorkList;

@Service
public class WorkListService {

	@Autowired
	private WorkListDao workListDao;
	
	public List<WorkList> findAll(){
        return workListDao.findAll();
    }
	
	public WorkList findById(String id){
        return workListDao.findOne(id);
    }
	
//	public WorkList findByBk(String tenantId,String username){
//		User user=new User();
//		Tenant tenant=new Tenant();
//		tenant.setId(tenantId);
//		user.setTenant(tenant);
//		user.setUsername(username);
//		return workListDao.findByBk(workList);
//    }
	
//	public void save(WorkList workList) throws Exception {
//		workList.setCreatedAt(new Timestamp(System.currentTimeMillis()));
//    	valBkNotNull(workList);
//		valBkNotExist(workList);
//		valNonBk(workList);
//		workListDao.create(workList);
//	}
//	
//	public void update(WorkList workList) throws Exception {
//		workList.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
//		valIdNotNull(workList);
//		valIdExist(workList.getId());
//		valBkNotNull(workList);
//		valBkNotChange(workList);
//		valNonBk(workList);
//		valCreatedNotChange(workList);
//		workListDao.update(workList);
//	}
	
	public void delete(String id) throws Exception {
		valIdExist(id);
		workListDao.deleteById(id);
	}
	
	private void valIdExist(String id)throws Exception{
		if(!workListDao.isIdExist(id)) {
			throw new Exception("Data tidak ada");
		}
	}
	
	private void valIdNotNull(WorkList workList)throws Exception {
		if(workList.getId()==null) {
			throw new Exception("Id tidak boleh kosong");
		}
	}
	
//	private void valNonBk(WorkList workList)throws Exception{
//		List<String> listErr = new ArrayList<String>();
//		
//		if(workList) {
//			listErr.add("Illegal Email Format");
//		}
//		
//		if(!listErr.isEmpty()) {
//			throw new ValidationException(listErr);
//		}
//	}
//	
//	private void valBkNotExist(WorkList workList)throws Exception{
//		if(workListDao.isBkExist(workList)) {
//			throw new Exception("Data sudah ada");
//		}
//	}	
//	
//	private void valBkNotChange(WorkList workList)throws Exception{
//		WorkList tempUser=findById(workList.getId());
//
//		if(!tempUser.getTenant().getId().equals(user.getTenant().getId()) || !tempUser.getUsername().equals(user.getUsername())) {
//			throw new Exception("BK tidak boleh berubah");
//		}
//	}
//	
//	private void valBkNotNull(WorkList workList) throws Exception{
//		if(workList.getTenant().getId() == null || workList.getUsername() == null) {
//			throw new Exception("Bk tidak boleh kosong");
//		}
//	}
//	
//	private void valCreatedNotChange(WorkList workList)throws Exception {
//		WorkList tempUser=findById(user.getId());
//		
//		if(!tempUser.getCreatedAt().equals(workList.getCreatedAt()) || !tempUser.getCreatedBy().equals(user.getCreatedBy())) {
//			throw new Exception("created tidak boleh berubah");
//		}
//	}
}
