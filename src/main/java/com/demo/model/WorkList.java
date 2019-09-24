package com.demo.model;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "core_worklists")
public class WorkList extends BaseEntity{
	private static final long serialVersionUID = 1L;

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
	    
	@Column(name = "notes")
    private String notes;
	
	@Column(name = "sub_type")
    private String subType;
	
	@Column(name = "description")
    private String description;
	
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
	
}
