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
@Table(name = "core_roles",uniqueConstraints = @UniqueConstraint(columnNames = {"company_id","name"}))
public class Role extends BaseEntity{
	private static final long serialVersionUID = 1L;
	
	@JoinColumn(name = "company_id", referencedColumnName = "id")
    @OneToOne()
    private Company company;
	
    @Column(name = "name")
    private String name;
    
    @Column(name = "is_deleted")
    private Boolean isDeleted;
        
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

}
