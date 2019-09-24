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
@Table(name = "core_positions", uniqueConstraints = @UniqueConstraint(columnNames = {"company_id", "code"}))
public class Position implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	@JoinColumn(name = "company_id", referencedColumnName = "id")
	@OneToOne
	private Company company;

	@JoinColumn(name = "unit_id", referencedColumnName = "id")
	@OneToOne
	private Unit unit;

	@JoinColumn(name = "job_id", referencedColumnName = "id")
	@OneToOne
	private Job job;

	@Column(name = "code")
	private String code;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "is_head")
	private Boolean isHead;

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
	
	@Column(name = "version", insertable = false)
	private Long version;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		if (company == null) {
			this.company = new Company();
		} else {
			this.company = company;	
		}
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		if (unit == null) {
			this.unit = new Unit();
		} else {
			this.unit = unit;
		}
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		if (job == null) {
			this.job = new Job();
		} else {
			this.job = job;	
		}
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		if (code == null) {
			this.code = new String();
		} else {
			this.code = code;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name == null) {
			this.name = new String();
		} else {
			this.name = name;	
		}
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		if (description == null) {
			this.description = new String();
		} else {
			this.description = description;	
		}	
	}

	public Boolean getIsHead() {
		return isHead;
	}

	public void setIsHead(Boolean isHead) {
		if (isHead == null) {
			this.isHead = false;
		} else {
			this.isHead = isHead;	
		}
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		if (createdBy == null) {
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
		if (updatedBy == null) {
			this.updatedBy = new String();
		} else {
			this.updatedBy = updatedBy;	
		}
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;	
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		if (version == null) {
			this.version = new Long (0);
		} else {
			this.version = version;	
		}
	}
}
