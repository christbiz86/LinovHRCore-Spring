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
		name = "core_working_condition_types", 
		uniqueConstraints = @UniqueConstraint(
				columnNames = {"company_id", "code"}
				)
		)
public class WorkingConditionType extends BaseEntity {
	
	@OneToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;
	
	@Column(name = "code")
    private String code;
	
	@Column(name = "name")
    private String name;
	
	@Column(name = "description")
    private String description;

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
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
