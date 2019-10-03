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
@Table(name = "core_location_groups", uniqueConstraints = @UniqueConstraint(columnNames = {"code", "company_id"}))
public class LocationGroup extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	@Column(name = "code")
	private String code;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "is_deleted")
	private Boolean isDeleted = false;
	
	@JoinColumn(name = "company_id", referencedColumnName = "id")
	@OneToOne
	private Company company;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		if(code == null) {
			this.code = new String();
		} else {
			this.code = code;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(name == null) {
			this.name = new String();
		} else {
			this.name = name;
		}
	}

	public Boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(Boolean isDeleted) {
		if(isDeleted == null) {
			this.isDeleted = false;
		} else {
			this.isDeleted = isDeleted;
		}
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		if(company == null) {
			this.company = new Company();
		} else {
			this.company = company;
		}
	}
}
