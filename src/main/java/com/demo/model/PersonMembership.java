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
@Table(name = "core_person_memberships", uniqueConstraints = @UniqueConstraint(columnNames = {"person_id", "lov_mbty"}))
public class PersonMembership extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	@Column(name = "acc_number")
	private String accNumber;
	
	@JoinColumn(name = "person_id", referencedColumnName = "id")
	@OneToOne
	private Person person;
	
	@JoinColumn(name = "lov_mbty", referencedColumnName = "id")
	@OneToOne
	private Lov lovMemberType;

	public String getAccNumber() {
		return accNumber;
	}

	public void setAccNumber(String accNumber) {
		if(accNumber == null) {
			this.accNumber = new String();
		} else {
			this.accNumber = accNumber;
		}
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		if(person == null) {
			this.person = new Person();
		} else {
			this.person = person;
		}
	}

	public Lov getLovMemberType() {
		return lovMemberType;
	}

	public void setLovMemberType(Lov lovMemberType) {
		if(lovMemberType == null) {
			this.lovMemberType = new Lov();
		} else {
			this.lovMemberType = lovMemberType;
		}
	}
}
