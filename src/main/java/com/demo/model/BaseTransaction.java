package com.demo.model;

import java.security.Timestamp;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

@MappedSuperclass
public abstract class BaseTransaction extends BaseEntity{
	private static final long serialVersionUID = 1L;
	
	@OneToOne
	@JoinColumn(name="company_id", referencedColumnName="id")
	private Company company;
	
	@JoinColumn(name="trx_code_id", referencedColumnName="id")
	private TrxCode trxCode;
	
	@Column(name="trx_number")
	private Long trxNumber;
	
	@Column(name="trx_date")
	private Timestamp trxDate;

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public TrxCode getTrxCode() {
		return trxCode;
	}

	public void setTrxCode(TrxCode trxCode) {
		this.trxCode = trxCode;
	}

	public Long getTrxNumber() {
		return trxNumber;
	}

	public void setTrxNumber(Long trxNumber) {
		this.trxNumber = trxNumber;
	}

	public Timestamp getTrxDate() {
		return trxDate;
	}

	public void setTrxDate(Timestamp trxDate) {
		this.trxDate = trxDate;
	}
}
