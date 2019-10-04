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
		name = "core_cost_centers", 
		uniqueConstraints = @UniqueConstraint(
				columnNames = {"company_id", "code"}
				)
		)
public class Costcenter extends BaseEntity{
	private static final long serialVersionUID = 1L;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @OneToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;
    
    @Column(name = "is_deleted")
    private Boolean isDeleted;

    public String getName(){
        return name;
    }

    public void setName(String name){
    	if(name == null) {
    		this.name = new String();
    	} else {
    		this.name = name;
    	}
    }

    public String getCode(){
        return code;
    }

    public void setCode(String code){
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
