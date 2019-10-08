package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.EmployeeStatusDao;
import com.demo.model.EmployeeStatus;

@Service
public class EmployeeStatusService {

	@Autowired
	private EmployeeStatusDao employeeStatusDao;
	
	public List<EmployeeStatus> findAll(){
        return employeeStatusDao.findAll();
    }
	
	public List<EmployeeStatus> findAll(Integer offset,Integer limit){
        return employeeStatusDao.findAll(offset, limit);
    }
	
	public EmployeeStatus findById(String id){
        return employeeStatusDao.findOne(id);
    }
	
	public EmployeeStatus findByBk(String companyId,String code){
		
		return employeeStatusDao.findByBk(companyId, code);
    }
	
	public void save(EmployeeStatus employeeStatus) throws Exception {
    	valBkNotNull(employeeStatus);
		valBkNotExist(employeeStatus);
		valNonBk(employeeStatus);
		employeeStatusDao.create(employeeStatus);
	}
	
	public void update(EmployeeStatus employeeStatus) throws Exception {
		valIdNotNull(employeeStatus);
		valIdExist(employeeStatus.getId());
		valBkNotNull(employeeStatus);
		valBkNotChange(employeeStatus);
		valNonBk(employeeStatus);
		employeeStatusDao.update(employeeStatus);
	}
		
	public void delete(String id) throws Exception {
		valIdExist(id);
		employeeStatusDao.deleteById(id);
	}

	private void valIdExist(String id)throws Exception{
		if(!employeeStatusDao.isIdExist(id)) {
			throw new Exception("Data tidak ada");
		}
	}
	
	private void valIdNotNull(EmployeeStatus employeeStatus)throws Exception {
		if(employeeStatus.getId().isEmpty()) {
			throw new Exception("Id tidak boleh kosong");
		}
	}
	
	private void valNonBk(EmployeeStatus employeeStatus)throws Exception{
		if(employeeStatus.getName().isEmpty()) {
			throw new Exception("Name tidak boleh kosong");
		}
		if(employeeStatus.getGeneratePaklaring()==null) {
			throw new Exception("Generate Paklaring tidak boleh kosong");
		}
		if(employeeStatus.getIsPermanent()==null) {
			throw new Exception("IsPermanent tidak boleh kosong");
		}
	}
	
	private void valBkNotExist(EmployeeStatus employeeStatus)throws Exception{
		if(employeeStatusDao.isBkExist(employeeStatus)) {
			throw new Exception("Data sudah ada");
		}
	}	
	
	private void valBkNotChange(EmployeeStatus employeeStatus)throws Exception{
		EmployeeStatus tempEmployeeStatus=findById(employeeStatus.getId());

		if(!tempEmployeeStatus.getCompany().getId().equals(employeeStatus.getCompany().getId()) || !tempEmployeeStatus.getCode().equals(employeeStatus.getCode())) {
			throw new Exception("BK tidak boleh berubah");
		}
	}
	
	private void valBkNotNull(EmployeeStatus employeeStatus) throws Exception{
		if(employeeStatus.getCompany().getId().isEmpty() || employeeStatus.getCode().isEmpty()) {
			throw new Exception("Bk tidak boleh kosong");
		}
	}
	
	private void valCreatedNotChange(EmployeeStatus employeeStatus)throws Exception {
		EmployeeStatus tempEmployeeStatus=findById(employeeStatus.getId());
		
		if(!tempEmployeeStatus.getCreatedAt().equals(employeeStatus.getCreatedAt()) || !tempEmployeeStatus.getCreatedBy().equals(employeeStatus.getCreatedBy())) {
			throw new Exception("created tidak boleh berubah");
		}
	}
}
