package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.AssignmentDao;
import com.demo.model.Assignment;

@Service
public class AssignmentService {

	@Autowired
	private AssignmentDao assignmentDao;
	
	public List<Assignment> findAll(){
        return assignmentDao.findAll();
    }
	
	public List<Assignment> findAll(Integer offset,Integer limit){
        return assignmentDao.findAll(offset, limit);
    }
	
	public Assignment findById(String id){
        return assignmentDao.findOne(id);
    }
	
	public Assignment findByBk(String companyId,String name){
		
		return assignmentDao.findByBk(companyId, name);
    }
	
	public void save(Assignment assignment) throws Exception {
    	valBkNotNull(assignment);
		valBkNotExist(assignment);
		valNonBk(assignment);
		assignmentDao.create(assignment);
	}
	
	public void update(Assignment assignment) throws Exception {
		valIdNotNull(assignment);
		valIdExist(assignment.getId());
		valBkNotNull(assignment);
		valBkNotChange(assignment);
		valNonBk(assignment);
		assignmentDao.update(assignment);
	}
		
	public void delete(String id) throws Exception {
		valIdExist(id);
		assignmentDao.deleteById(id);
	}

	private void valIdExist(String id)throws Exception{
		if(!assignmentDao.isIdExist(id)) {
			throw new Exception("Data tidak ada");
		}
	}
	
	private void valIdNotNull(Assignment assignment)throws Exception {
		if(assignment.getId().isEmpty()) {
			throw new Exception("Id tidak boleh kosong");
		}
	}
	
	private void valNonBk(Assignment assignment)throws Exception{
		if(assignment.getEmployeeStatus().getId().isEmpty()) {
			throw new Exception("IsDeleted tidak boleh kosong");
		}
		if(assignment.getAssignmentReason().getId().isEmpty()){
			throw new Exception("Assignment Reason tidak boleh kosong");
		}
		if(assignment.getIsPrimary()==null) {
			throw new Exception("IsPrimary tidak boleh kosong");
		}
		if(assignment.getLovActy().getId().isEmpty()) {
			throw new Exception("Lov tidak boleh kosong");
		}
	}
	
	private void valBkNotExist(Assignment assignment)throws Exception{
		if(assignmentDao.isBkExist(assignment)) {
			throw new Exception("Data sudah ada");
		}
	}	
	
	private void valBkNotChange(Assignment assignment)throws Exception{
		Assignment tempAssignment=findById(assignment.getId());

		if(!tempAssignment.getCompany().getId().equals(assignment.getCompany().getId()) || !tempAssignment.getPositionSlot().getId().equals(assignment.getPositionSlot().getId())) {
			throw new Exception("BK tidak boleh berubah");
		}
	}
	
	private void valBkNotNull(Assignment assignment) throws Exception{
		if(assignment.getCompany().getId().isEmpty() || assignment.getPositionSlot().getId().isEmpty()) {
			throw new Exception("Bk tidak boleh kosong");
		}
	}
	
	private void valCreatedNotChange(Assignment assignment)throws Exception {
		Assignment tempAssignment=findById(assignment.getId());
		
		if(!tempAssignment.getCreatedAt().equals(assignment.getCreatedAt()) || !tempAssignment.getCreatedBy().equals(assignment.getCreatedBy())) {
			throw new Exception("created tidak boleh berubah");
		}
	}
}
