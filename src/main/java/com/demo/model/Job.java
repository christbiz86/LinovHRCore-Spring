package com.demo.model;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
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
public class Job extends BaseEntity {


	@Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "ordinal")
    private Integer ordinal;

    @OneToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;

    public String getName(){
        return name;
    }

    public void setName(String name) {
    	if(name.isEmpty()) {
    		this.name = new String();
    	} else {
    		this.name = name;
    	}
	}

    public String getCode(){
        return code;
    }

	public void setCode(String code) {
		if(code.isEmpty()) {
			this.code = new String();
		} else {
			this.code = code;
		}
	}
	
    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		if(description.isEmpty()) {
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
