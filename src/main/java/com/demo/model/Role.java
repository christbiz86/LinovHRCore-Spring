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
@Table(name = "core_roles",uniqueConstraints = @UniqueConstraint(columnNames = {"company_id","name"}))
public class Role implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	@JoinColumn(name = "company_id", referencedColumnName = "id")
    @OneToOne()
    private Company company;
	
    @Column(name = "name")
    private String name;
    
    @Column(name = "is_deleted")
    private Boolean isDeleted;
    
    @Column(name = "created_by")
    private String createdBy;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+7")
    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_by")
    private String updatedBy;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+7")
    @Column(name = "updated_at")
    private Timestamp updatedAt;
    
    @Column(name = "description")
    private String description;
    
	@Column(name = "version")
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
		if(company==null) {
			this.company=new Company();
		}else {
			this.company = company;			
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(name==null) {
			this.name=new String();
		}else {
			this.name = name;			
		}
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		if(isDeleted==null) {
			this.isDeleted=false;
		}else {
			this.isDeleted = isDeleted;			
		}
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		if(description==null) {
			this.description=new String();
		}else {
			this.description = description;
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
