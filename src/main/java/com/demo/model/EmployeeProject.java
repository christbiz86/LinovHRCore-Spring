package com.demo.model;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "core_employee_projects", uniqueConstraints = @UniqueConstraint(columnNames = {"project_id", "employee_id"}))
public class EmployeeProject extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	@Column(name = "weight")
	private Integer weight;
	
	@JoinColumn(name = "project_id", referencedColumnName = "id")
	@OneToOne
	private Project project;
	
	@JoinColumn(name = "employee_id", referencedColumnName = "id")
	@OneToOne
	private Employee employee;

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}
