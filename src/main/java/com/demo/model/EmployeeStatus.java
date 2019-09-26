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
@Table(name="core_employee_statuses",uniqueConstraints = @UniqueConstraint(columnNames = {"company_id","code"}))
public class EmployeeStatus extends BaseEntity{
	private static final long serialVersionUID = 1L;
	
	@JoinColumn(name = "company_id", referencedColumnName = "id")
	@OneToOne
	private Company company;
	
	@Column(name="code")
	private String code;
	
	@Column(name="name")
	private String name;
	
	@Column(name="code")
	private Integer workingMonth;

	@Column(name="payroll_group_code")
	private String payrollGroupCode;

	@Column(name="benefit_group_code")
	private String benefitGroupCode;

	@Column(name="code")
	private Boolean generatePaklaring;

	@Column(name="paklaring_templates_id")
	private String paklaringTemplatesId;
	
	@Column(name="employee_status_document_templates_id")
	private String employeeStatusDocumentTemplatesId;

	@Column(name="is_permanent")
	private Boolean isPermanent;

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

	public Integer getWorkingMonth() {
		return workingMonth;
	}

	public void setWorkingMonth(Integer workingMonth) {
		if(workingMonth==null) {
			this.workingMonth = 0;
		}else {
			this.workingMonth = workingMonth;			
		}
	}

	public String getPayrollGroupCode() {
		return payrollGroupCode;
	}

	public void setPayrollGroupCode(String payrollGroupCode) {
		if(payrollGroupCode==null) {
			this.payrollGroupCode = new String();
		}else {
			this.payrollGroupCode = payrollGroupCode;
		}
	}

	public String getBenefitGroupCode() {
		return benefitGroupCode;
	}

	public void setBenefitGroupCode(String benefitGroupCode) {
		if(benefitGroupCode==null) {
			this.benefitGroupCode = new String();
		}else {
			this.benefitGroupCode = benefitGroupCode;			
		}
	}

	public Boolean getGeneratePaklaring() {
		return generatePaklaring;
	}

	public void setGeneratePaklaring(Boolean generatePaklaring) {
		if(generatePaklaring==null) {
			this.generatePaklaring = false;
		}else {
			this.generatePaklaring = generatePaklaring;			
		}
	}

	public String getPaklaringTemplatesId() {
		return paklaringTemplatesId;
	}

	public void setPaklaringTemplatesId(String paklaringTemplatesId) {
		if(paklaringTemplatesId==null) {
			this.paklaringTemplatesId = new String();
		}else {
			this.paklaringTemplatesId = paklaringTemplatesId;			
		}
	}

	public String getEmployeeStatusDocumentTemplatesId() {
		return employeeStatusDocumentTemplatesId;
	}

	public void setEmployeeStatusDocumentTemplatesId(String employeeStatusDocumentTemplatesId) {
		if(employeeStatusDocumentTemplatesId==null) {
			this.employeeStatusDocumentTemplatesId = new String();
		}else {
			this.employeeStatusDocumentTemplatesId = employeeStatusDocumentTemplatesId;			
		}
	}

	public Boolean getIsPermanent() {
		return isPermanent;
	}

	public void setIsPermanent(Boolean isPermanent) {
		if(isPermanent==null) {
			this.isPermanent = false;
		}else {
			this.isPermanent = isPermanent;			
		}
	}
}
