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
		name = "core_working_conditions", 
		uniqueConstraints = @UniqueConstraint(
				columnNames = {"working_condition_type_id", "code"}
				)
		)
public class WorkingCondition extends BaseEntity {
	public static final long serialVersionUID = 1L;
	
	@OneToOne
    @JoinColumn(name = "working_condition_type_id", referencedColumnName = "id")
    private WorkingConditionType workingConditionType;
	
	@Column(name = "code")
    private String code;
	
	@Column(name = "name")
    private String name;
	
	@Column(name = "description")
    private String description;

	public WorkingConditionType getWorkingConditionType() {
		return workingConditionType;
	}

	public void setWorkingConditionType(WorkingConditionType workingConditionType) {
		this.workingConditionType = workingConditionType;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
