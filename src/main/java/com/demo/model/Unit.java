package com.demo.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "core_units")
public class Unit {
	@Id
    @Column(name = "id")
    @GenericGenerator(name="UUID", strategy="org.hibernate.id.UUIDGenerator")
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
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
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
		this.company = company;
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
		this.version = version;
	}
}
