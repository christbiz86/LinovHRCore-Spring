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
		name = "core_duties", 
		uniqueConstraints = @UniqueConstraint(
				columnNames = {"company_id", "responsibility_id"}
				)
		)
public class Duty extends BaseEntity {
	
	@OneToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;
	
	@OneToOne
    @JoinColumn(name = "responsibility_id", referencedColumnName = "id")
    private Responsibility responsibility;
	
	@Column(name = "description")
    private String description;

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Responsibility getResponsibility() {
		return responsibility;
	}

	public void setResponsibility(Responsibility responsibility) {
		this.responsibility = responsibility;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
