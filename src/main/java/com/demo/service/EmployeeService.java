package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.EmployeeDao;
import com.demo.model.Employee;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;
	
	public List<Employee> findAll(){
        return employeeDao.findAll();
    }
	
	public List<Employee> findAll(Integer offset,Integer limit){
        return employeeDao.findAll(offset, limit);
    }
	
	public Employee findById(String id){
        return employeeDao.findOne(id);
    }
	
	public Employee findByBk(String companyId,String name){
		
		return employeeDao.findByBk(companyId, name);
    }
	
	public void save(Employee employee) throws Exception {
    	valBkNotNull(employee);
		valBkNotExist(employee);
		valNonBk(employee);
		employeeDao.create(employee);
	}
	
	public void update(Employee employee) throws Exception {
		valIdNotNull(employee);
		valIdExist(employee.getId());
		valBkNotNull(employee);
		valBkNotChange(employee);
		valNonBk(employee);
		employeeDao.update(employee);
	}
		
	public void delete(String id) throws Exception {
		valIdExist(id);
		employeeDao.deleteById(id);
	}

	private void valIdExist(String id)throws Exception{
		if(!employeeDao.isIdExist(id)) {
			throw new Exception("Data tidak ada");
		}
	}
	
	private void valIdNotNull(Employee employee)throws Exception {
		if(employee.getId().isEmpty()) {
			throw new Exception("Id tidak boleh kosong");
		}
	}
	
	private void valNonBk(Employee employee)throws Exception{
		
	}
	
	private void valBkNotExist(Employee employee)throws Exception{
		if(employeeDao.isBkExist(employee)) {
			throw new Exception("Data sudah ada");
		}
	}	
	
	private void valBkNotChange(Employee employee)throws Exception{
		Employee tempEmployee=findById(employee.getId());

		if(!tempEmployee.getPerson().getId().equals(employee.getPerson().getId()) || !tempEmployee.getAssignment().equals(employee.getAssignment())) {
			throw new Exception("BK tidak boleh berubah");
		}
	}
	
	private void valBkNotNull(Employee employee) throws Exception{
		if(employee.getPerson().getId().isEmpty() || employee.getAssignment().getId().isEmpty()) {
			throw new Exception("Bk tidak boleh kosong");
		}
	}
	
	private void valCreatedNotChange(Employee employee)throws Exception {
		Employee tempEmployee=findById(employee.getId());
		
		if(!tempEmployee.getCreatedAt().equals(employee.getCreatedAt()) || !tempEmployee.getCreatedBy().equals(employee.getCreatedBy())) {
			throw new Exception("created tidak boleh berubah");
		}
	}
}
