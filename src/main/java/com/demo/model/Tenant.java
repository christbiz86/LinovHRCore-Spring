package com.demo.model;

import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(
		name = "core_tenants", 
		uniqueConstraints = @UniqueConstraint(
				columnNames = {"name", "code"}
				)
		)
public class Tenant extends BaseEntity {

    @Column(name = "name")
    private String name;
    
    @Column(name = "code")
    private String code;
    
    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    @OneToMany(mappedBy = "tenant",fetch = FetchType.LAZY)
    private List<Company> companies;

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
		if(code == null) {
			this.code = new String();
		} else {
			this.code = code;
		}
	}
	

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		if(isDeleted == null) {
			this.isDeleted = false;
    	} else {
    		this.isDeleted = isDeleted;
    	}
	}

	public List<Company> getCompanies() {
		return companies;
	}

	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}
	
}
