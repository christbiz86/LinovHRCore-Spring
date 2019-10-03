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
@Table(
		name = "core_job_working_conditions", 
		uniqueConstraints = @UniqueConstraint(
				columnNames = {"job_id"}
				)
		)
public class JobWorkingCondition extends BaseEntity {
	
	@OneToOne
    @JoinColumn(name = "job_id", referencedColumnName = "id")
	private Job job;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "is_essential")
	private Boolean isEssential = false;
	
	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		if(job == null) {
    		this.job = new Job();
    	} else {
    		this.job = job;
    	}
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		if(description == null) {
			this.description = new String();
    	} else {
    		this.description = description;
    	}
	}

	public Boolean getIsEssential() {
		return isEssential;
	}

	public void setIsEssential(Boolean isEssential) {
		if(isEssential == null) {
			this.isEssential = false;
    	} else {
    		this.isEssential = isEssential;
    	}
	}
	
}
