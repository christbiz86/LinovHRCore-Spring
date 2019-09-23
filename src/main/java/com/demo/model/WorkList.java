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
@Table(name = "core_worklists")
public class WorkList implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	@JoinColumn(name = "company_id", referencedColumnName = "id")
    @OneToOne()
    private Company company;
	
	@JoinColumn(name = "lov_wfty", referencedColumnName = "id")
    @OneToOne()
    private Lov lovWfty;

	@Column(name = "request_id")
    private String requestId;
	
	@Column(name = "ordinal")
    private Integer ordinal;
	
	@Column(name = "requester_id")
    private String requesterId;
	
	@Column(name = "approver_id")
    private String approverId;
	
	@Column(name = "answer")
    private String answer;
	
	@Column(name = "is_active")
    private Boolean isActive;
	
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
    
	@Column(name = "notes")
    private String notes;
	
	@Column(name = "sub_type")
    private String subType;
	
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

	public Lov getLovWfty() {
		return lovWfty;
	}

	public void setLovWfty(Lov lovWfty) {
		if(lovWfty==null) {
			this.lovWfty=new Lov();
		}else {
			this.lovWfty = lovWfty;			
		}
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		if(requestId==null) {
			this.requesterId=new String();
		}else {
			this.requestId = requestId;			
		}
	}

	public Integer getOrdinal() {
		return ordinal;
	}

	public void setOrdinal(Integer ordinal) {
		if(ordinal==null) {
			this.ordinal=0;
		}else {
			this.ordinal = ordinal;	
		}
	}

	public String getRequesterId() {
		return requesterId;
	}

	public void setRequesterId(String requesterId) {
		if(requesterId==null) {
			this.requesterId=new String();
		}else {
			this.requesterId = requesterId;			
		}
	}

	public String getApproverId() {
		return approverId;
	}

	public void setApproverId(String approverId) {
		if(approverId==null) {
			this.approverId=new String();
		}else {
			this.approverId = approverId;			
		}
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		if(answer==null) {
			this.answer=new String();
		}else {
			this.answer = answer;	
		}
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		if(isActive==null) {
			this.isActive = true;
		}else {
			this.isActive = isActive;	
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

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		if(notes==null) {
			this.notes = new String();
		}else {
			this.notes = notes;	
		}
	}

	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		if(subType==null){
			this.subType = new String();
		}else {
			this.subType = subType;	
		}
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		if(description==null) {
			this.description = new String();
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
