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
@Table(
		name = "core_jobs", 
		uniqueConstraints = @UniqueConstraint(
				columnNames = {"company_id", "code"}
				)
		)
public class Job implements Serializable {
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "ordinal")
    private Integer ordinal;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_at")
    private Timestamp updatedAt;
    
    @Column(name = "version")
    private Long version;

    @OneToOne
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
    	if(name == null) {
    		this.name = new String();
    	} else {
    		this.name = name;
    	}
	}

    public String getCode(){
        return code;
    }

    public void setCode(String code) {
    	System.out.println(code);
    	if(code == null) {
    		System.out.println("111");
    		this.code = new String();
    	} else {
    		this.code = code;
    	}
	}

    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		if(description == null) {
			this.description = new String();
    	} else {
    		this.description = description;
    	}
	}

	public Integer getOrdinal() {
		return ordinal;
	}

	public void setOrdinal(Integer ordinal) {
		if(ordinal == null) {
			this.ordinal = 0;
    	} else {
    		this.ordinal = ordinal;
    	}
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

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		if(updatedBy == null) {
			this.updatedBy = new String();
    	} else {
    		this.updatedBy = updatedBy;
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

}
