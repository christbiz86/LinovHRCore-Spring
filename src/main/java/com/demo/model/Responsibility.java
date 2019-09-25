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
@Table(name="core_responsibilities",uniqueConstraints = @UniqueConstraint(columnNames = {"company_id","code"}))
public class Responsibility extends BaseEntity{
	private static final long serialVersionUID = 1L;
	
	@JoinColumn(name = "company_id", referencedColumnName = "id")
    @OneToOne
    private Company company;
	
    @JoinColumn(name="responsibility_group_id", referencedColumnName = "id")
    @OneToOne
    private ResponsibilityGroup responsibilityGroup;
    
    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;
            
    @Column(name = "used_for")
    private String usedFor;
    
    @Column(name = "used_for_value")
    private String usedForValue;
    
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		if(company==null) {
			this.company = new Company();
		}else {
			this.company = company;			
		}
	}

	public ResponsibilityGroup getResponsibilityGroup() {
		return responsibilityGroup;
	}

	public void setResponsibilityGroup(ResponsibilityGroup responsibilityGroup) {
		if(responsibilityGroup==null) {
			this.responsibilityGroup = new ResponsibilityGroup();
		}else {
			this.responsibilityGroup = responsibilityGroup;			
		}
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		if(code==null) {
			this.code = new String();			
		}else {
			this.code = code;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(name==null) {
			this.name = new String();			
		}else {
			this.name = name;
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

	public String getUsedFor() {
		return usedFor;
	}

	public void setUsedFor(String usedFor) {
		if(usedFor==null) {
			this.usedFor = new String();
		}else {
			this.usedFor = usedFor;			
		}
	}

	public String getUsedForValue() {
		return usedForValue;
	}

	public void setUsedForValue(String usedForValue) {
		if(usedForValue==null) {
			this.usedForValue = new String();			
		}else {
			this.usedForValue = usedForValue;
		}
	}

}