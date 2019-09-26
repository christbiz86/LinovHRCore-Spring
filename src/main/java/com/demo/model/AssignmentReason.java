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
		name = "core_assignment_reasons", 
		uniqueConstraints = @UniqueConstraint(
				columnNames = {"id_company","code"}
				)
		)
public class AssignmentReason extends BaseEntity {
	
	@OneToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;
	
	@OneToOne
    @JoinColumn(name = "lov_acty", referencedColumnName = "id")
    private Lov lov;
	
	@Column(name = "code")
    private String code;
    
    @Column(name = "description")
    private String description;

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Lov getLov() {
		return lov;
	}

	public void setLov(Lov lov) {
		this.lov = lov;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
