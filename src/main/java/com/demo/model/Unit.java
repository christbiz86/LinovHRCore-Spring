package com.demo.model;

import java.io.Serializable;
import java.sql.Timestamp;

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
@Table(name = "core_units", uniqueConstraints = @UniqueConstraint(columnNames = {"code", "company_id"}))
public class Unit implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	
	@Column(name = "code")
	private String code;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "created_at")
	private Timestamp createdAt;
	
	@Column(name = "updated_by")
	private String updatedBy;
	
	@Column(name = "updated_at")
	private Timestamp updatedAt;
	
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
	
	@Column(name = "version")
	private Long version;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		if(createdBy == null) {
			this.createdBy = new String();
		} else {
			this.createdBy = createdBy;
		}
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
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

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
}
