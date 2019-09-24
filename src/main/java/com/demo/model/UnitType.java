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

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "core_unit_types", uniqueConstraints = @UniqueConstraint(columnNames = {"code", "company_id"}))
public class UnitType implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	
	@Column(name = "code")
	private String code;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "unit_level")
	private Integer unitLevel;
	
	@Column(name = "created_by")
	private String createdBy;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+7")
	@Column(name = "created_at")
	private Timestamp createdAt;
	
	@Column(name = "updated_by")
	private String updatedBy;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+7")
	@Column(name = "updated_at")
	private Timestamp updatedAt;
	
	@JoinColumn(name = "company_id", referencedColumnName = "id")
	@OneToOne
	private Company company;
	
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

	public Integer getUnitLevel() {
		return unitLevel;
	}

	public void setUnitLevel(Integer unitLevel) {
		if(unitLevel == null) {
			this.unitLevel = new Integer(0);
		} else {
			this.unitLevel = unitLevel;
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
