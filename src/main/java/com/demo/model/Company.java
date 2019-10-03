package com.demo.model;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name="core_companies", uniqueConstraints = @UniqueConstraint(columnNames = {"code", "tenant_id"}))
public class Company extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@Column(name = "code")
	private String code;
	
    @Column(name = "name")
    private String name;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "company_tax_number")
    private String companyTaxNumber;
    
    @Column(name = "is_deleted")
    private Boolean isDeleted = false;
    
    @Column(name = "file_logo")
    private String fileLogo;
    
    @Column(name = "tax_withholder_number")
    private String taxWithHolderNumber;
    
    @Column(name = "tax_withholder_name")
    private String taxWithHolderName;
    
    @Column(name = "sort_order")
    private Integer sortOrder = 0;

    @JsonIgnoreProperties(value = {"companies"})
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "tenant_id", referencedColumnName = "id")
    private Tenant tenant;

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

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		if(code == null) {
			this.code = new String();
		} else {
			this.code = code;
		}
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCompanyTaxNumber() {
		return companyTaxNumber;
	}

	public void setCompanyTaxNumber(String companyTaxNumber) {
		if(companyTaxNumber == null) {
			this.companyTaxNumber = new String();
		} else {
			this.companyTaxNumber = companyTaxNumber;
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

	public String getFileLogo() {
		return fileLogo;
	}

	public void setFileLogo(String fileLogo) {
		this.fileLogo = fileLogo;
	}

	public String getTaxWithHolderNumber() {
		return taxWithHolderNumber;
	}

	public void setTaxWithHolderNumber(String taxWithHolderNumber) {
		this.taxWithHolderNumber = taxWithHolderNumber;
	}

	public String getTaxWithHolderName() {
		return taxWithHolderName;
	}

	public void setTaxWithHolderName(String taxWithHolderName) {
		this.taxWithHolderName = taxWithHolderName;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		if(sortOrder == null) {
			this.sortOrder = new Integer(0);
		} else {
			this.sortOrder = sortOrder;
		}
	}
}
