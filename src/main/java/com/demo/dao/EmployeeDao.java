package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.Employee;

@Repository
public class EmployeeDao extends AbstractJpaDao<Employee>{

	public EmployeeDao() {
		setClazz(Employee.class);
	}

	@SuppressWarnings("unchecked")
    public List<Employee> findAll(Integer offset, Integer limit){
        return super.entityManager.createQuery("FROM Employee")
                .setFirstResult(offset).setMaxResults(limit).getResultList();
    }

	@SuppressWarnings("unchecked")
	public Employee findByBk(String personId,String assignmentId) {
		List<Employee> list= super.entityManager.createQuery("FROM Employee WHERE person.id=:personId AND assignment.id=:assignmentId ")
				.setParameter("personId", personId)
				.setParameter("assignmentId", assignmentId)
				.getResultList();
		
		if (list.size() == 0) {
			return new Employee();
		}
		else {
			return (Employee)list.get(0);
		}
	}
	
	public boolean isBkExist(Employee employee) {
		if(findByBk(employee.getPerson().getId(),employee.getAssignment().getId()).getId() == null) {
			return false;
		} else {
			return true;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Employee> findByPerson(String personId) {
		List<Employee> list= super.entityManager.createQuery("FROM Employee WHERE person.id=:personId ")
				.setParameter("personId", personId)
				.getResultList();
		
			return list;
	}
}
