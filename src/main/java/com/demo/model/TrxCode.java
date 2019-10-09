package com.demo.model;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(
		name = "core_trx_code", 
		uniqueConstraints = @UniqueConstraint(
				columnNames = {"trx_code"}
				)
		)
public class TrxCode extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	@Column(name="trx_code")
	private String trxCode;
	
	@Column(name="Description")
	private String description;
	
	@Column(name="trx_name")
	private String trxName;

	public String getTrxCode() {
		return trxCode;
	}

	public void setTrxCode(String trxCode) {
		this.trxCode = trxCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTrxName() {
		return trxName;
	}

	public void setTrxName(String trxName) {
		this.trxName = trxName;
	}
}
