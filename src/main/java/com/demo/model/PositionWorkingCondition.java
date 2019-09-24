package com.demo.model;

import java.io.Serializable;
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

import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "core_position_working_conditions")
public class PositionWorkingCondition implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	@JoinColumn(name = "position_id", referencedColumnName = "id")
	@OneToOne
	private Position position;

	@Column(name = "description")
	private String description;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_at")
	private Timestamp createdAt;

	@Column(name = "updated_by")
	private String updatedBy;

	@Column(name = "updated_at")
	private Timestamp updatedAt;
	
	@Column(name = "is_essential")
	private Boolean isEssential;
	
	@Column(name = "version")
	private Long version;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		if (createdBy == null) {
			this.createdBy = new String();
		} else {
			this.createdBy = createdBy;
		}
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		if (updatedBy == null) {
			this.updatedBy = new String();
		} else {
			this.updatedBy = updatedBy;	
		}
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Boolean getIsEssential() {
		return isEssential;
	}

	public void setIsEssential(Boolean isEssential) {
		if (isEssential == null) {
			this.isEssential = false;
		} else {
			this.isEssential = isEssential;
		}
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		if (version == null) {
			this.version = new Long (0);
		} else {
			this.version = version;
		}
	}
}
