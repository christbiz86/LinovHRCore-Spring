package com.demo.model;

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
@Table(name = "core_position_slots", uniqueConstraints = @UniqueConstraint(columnNames = {"company_id", "code", "position_id"}))
public class PositionSlot extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@JoinColumn(name = "position_id", referencedColumnName = "id")
	@OneToOne
	private Position position;

	@Column(name = "code")
	private String code;
	
	@JoinColumn(name = "company_id", referencedColumnName = "id")
	@OneToOne
	private Company company;
	
	@Column(name = "version")
	private Long version;

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		if (position == null) {
			this.position = new Position();
		} else {
			this.position = position;	
		}
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		if (code == null) {
			this.code = new String();
		} else {
			this.code = code;	
		}
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		if (company == null) {
			this.company = new Company();
		} else {
			this.company = company;	
		}
	}
}
