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
@Table(name = "core_units", uniqueConstraints = @UniqueConstraint(columnNames = {"code", "company_id"}))
public class Unit extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	@Column(name = "code")
	private String code;
	
	@Column(name = "name")
	private String name;
	
	@JoinColumn(name = "company_id", referencedColumnName = "id")
	@OneToOne
	private Company company;
	
	@JoinColumn(name = "unit_type_id", referencedColumnName = "id")
	@OneToOne
	private UnitType unitType;
	
	@JoinColumn(name = "cost_center_id", referencedColumnName = "id")
	@OneToOne
	private Costcenter costCenter;
	
	@JoinColumn(name = "location_id", referencedColumnName = "id")
	@OneToOne
	private Location location;

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

	public UnitType getUnitType() {
		return unitType;
	}

	public void setUnitType(UnitType unitType) {
		this.unitType = unitType;
	}

	public Costcenter getCostCenter() {
		return costCenter;
	}

	public void setCostCenter(Costcenter costCenter) {
		this.costCenter = costCenter;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
}
