package com.demo.model;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "core_position_responsibilities")
public class PositionResponsibility extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@JoinColumn(name = "position_id", referencedColumnName = "id")
	@OneToOne
	private Position position;

	@Column(name = "description")
	private String description;
	
	@Column(name = "is_appraisal")
	private Boolean isAppraisal = false;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		if (description == null) {
			this.description = new String();
		} else {
			this.description = description;	
		}
	}
	
	public Boolean getIsAppraisal() {
		return isAppraisal;
	}

	public void setIsAppraisal(Boolean isAppraisal) {
		if (isAppraisal == null) {
			this.isAppraisal = false;
		} else {
			this.isAppraisal = isAppraisal;			
		}
	}
}
