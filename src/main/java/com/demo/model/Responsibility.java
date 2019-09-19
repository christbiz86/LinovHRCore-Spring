package com.demo.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="core_responsibilities")
public class Responsibility {

	@Id
    @Column(name = "id")
    @GenericGenerator(name="UUID", strategy="org.hibernate.id.UUIDGenerator")
	private String id;
	
	@JoinColumn(name = "company_id", referencedColumnName = "id")
    @OneToOne
    private Company company;
	
    @ManyToOne(optional = false,fetch = FetchType.EAGER)
    @JoinColumn(name="responsibility_group_id", referencedColumnName = "id")
    private ResponsibilityGroup responsibilityGroup;
    
    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;
    
    @Column(name = "eff_begin")
	private Date effBegin;
	
	@Column(name = "eff_end")
	private Date effEnd;
    
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
    
    @Column(name = "used_for")
    private String usedFor;
    
    @Column(name = "used_for_value")
    private String usedForValue;

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
		this.company = company;
	}

	public ResponsibilityGroup getResponsibilityGroup() {
		return responsibilityGroup;
	}

	public void setResponsibilityGroup(ResponsibilityGroup responsibilityGroup) {
		this.responsibilityGroup = responsibilityGroup;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getUsedFor() {
		return usedFor;
	}

	public void setUsedFor(String usedFor) {
		this.usedFor = usedFor;
	}

	public String getUsedForValue() {
		return usedForValue;
	}

	public void setUsedForValue(String usedForValue) {
		this.usedForValue = usedForValue;
	}

	public Date getEffBegin() {
		return effBegin;
	}

	public void setEffBegin(Date effBegin) {
		this.effBegin = effBegin;
	}

	public Date getEffEnd() {
		return effEnd;
	}

	public void setEffEnd(Date effEnd) {
		this.effEnd = effEnd;
	}
    
}
