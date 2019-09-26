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
@Table(name="core_employees",uniqueConstraints = @UniqueConstraint(columnNames = {"person_id","assignment_id"}))
public class Employee extends BaseEntity{
	private static final long serialVersionUID = 1L;

	@Column(name="nik")
	private String nik;
	
	@JoinColumn(name = "person_id", referencedColumnName = "id")
    @OneToOne
    private Person person;

	@JoinColumn(name = "assignment_id", referencedColumnName = "id")
    @OneToOne
	private Assignment assignment;

	public String getNik() {
		return nik;
	}

	public void setNik(String nik) {
		if(nik==null) {
			this.nik = new String();
		}else {
			this.nik = nik;			
		}
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		if(person==null) {
			this.person = new Person();
		}else {
			this.person = person;			
		}
	}

	public Assignment getAssignment() {
		return assignment;
	}

	public void setAssignment(Assignment assignment) {
		if(assignment==null) {
			this.assignment = new Assignment();
		}else {
			this.assignment = assignment;			
		}
	}
}
