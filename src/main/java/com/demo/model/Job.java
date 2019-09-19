package com.demo.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "core_jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "ordinal")
    private String ordinal;

    @Column(name = "created_by")
    private Integer createdBy;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+7")
    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_by")
    private Integer updatedBy;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+7")
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+7")
    @Column(name = "eff_begin")
    private Timestamp effBegin;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+7")
    @Column(name = "eff_end")
    private Timestamp effEnd;

    @JsonIgnoreProperties(value = {"jobs"})
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
		this.name = name;
	}

    public String getCode(){
        return code;
    }

    public void setCode(String code) {
		this.code = code;
	}    

    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOrdinal() {
		return ordinal;
	}

	public void setOrdinal(String ordinal) {
		this.ordinal = ordinal;
	}
	
	public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Timestamp getEffBegin() {
        return effBegin;
    }

    public void setEffBegin(Timestamp effBegin) {
        this.effBegin = effBegin;
    }

    public Timestamp getEffEnd() {
        return effEnd;
    }

    public void setEffEnd(Timestamp effEnd) {
        this.effEnd = effEnd;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

}
