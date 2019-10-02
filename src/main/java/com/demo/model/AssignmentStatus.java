package com.demo.model;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(
		name = "core_assignment_statuses", 
		uniqueConstraints = @UniqueConstraint(
				columnNames = {"assignment_id", "lov_asta"}
				)
		)
public class AssignmentStatus extends BaseEntity {
	public static final long serialVersionUID = 1L;
	
	@OneToOne
	@JoinColumn(name = "assignment_id", referencedColumnName = "id")
	private Assignment assignment;
	
	@OneToOne
	@JoinColumn(name = "lov_asta", referencedColumnName = "id")
	private Lov lovAsta;

	public Assignment getAssignment() {
		return assignment;
	}

	public void setAssignment(Assignment assignment) {
		if(assignment == null) {
    		this.assignment = new Assignment();
    	} else {
    		this.assignment = assignment;
    	}
	}

	public Lov getLovAsta() {
		return lovAsta;
	}

	public void setLovAsta(Lov lovAsta) {
		if(lovAsta == null) {
    		this.lovAsta = new Lov();
    	} else {
    		this.lovAsta = lovAsta;
    	}
	}

}
