package com.demo.model;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(
		name = "core_job_grades", 
		uniqueConstraints = @UniqueConstraint(
				columnNames = {"job_id", "grade_id"}
				)
		)
public class JobGrade implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;
	
	@OneToOne
    @JoinColumn(name = "job_id", referencedColumnName = "id")
	private Job job;
	
	@OneToOne
    @JoinColumn(name = "grade_id", referencedColumnName = "id")
	private Grade grade;
	
	@Column(name = "bottom_rate")
	private Integer bottomRate;
	
	@Column(name = "mid_rate")
	private Integer midRate;
	
	@Column(name = "top_rate")
	private Integer topRate;
	
	@Column(name = "version")
    private Long version;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		if(grade == null) {
    		this.grade = new Grade();
    	} else {
    		this.grade = grade;
    	}
	}

	public Integer getBottomRate() {
		return bottomRate;
	}

	public void setBottomRate(Integer bottomRate) {
		if(bottomRate == null) {
    		this.bottomRate = 0;
    	} else {
    		this.bottomRate = bottomRate;
    	}
	}

	public Integer getMidRate() {
		return midRate;
	}

	public void setMidRate(Integer midRate) {
		if(midRate == null) {
    		this.midRate = 0;
    	} else {
    		this.midRate = midRate;
    	}
	}

	public Integer getTopRate() {
		return topRate;
	}

	public void setTopRate(Integer topRate) {
		if(topRate == null) {
    		this.topRate = 0;
    	} else {
    		this.topRate = topRate;
    	}
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		if(version == null) {
    		this.version = new Long(0);
    	} else {
    		this.version = version;
    	}
	}

}
