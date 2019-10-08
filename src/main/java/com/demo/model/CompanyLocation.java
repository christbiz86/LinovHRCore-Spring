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
@Table(name = "core_company_locations", uniqueConstraints = @UniqueConstraint(columnNames = {"location_id", "company_id"}))
public class CompanyLocation extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	@JoinColumn(name = "company_id", referencedColumnName = "id")
	@OneToOne
	private Company company;
	
	@JoinColumn(name = "location_id", referencedColumnName = "id")
	@OneToOne
	private Location location;

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

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		if(location == null) {
			this.location = new Location();
		} else {
			this.location = location;
		}
	}
}
