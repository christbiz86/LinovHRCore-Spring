package com.demo.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {
	public static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

	@Column(name = "created_by")
<<<<<<< HEAD
	private String createdBy;

	@Column(name = "created_at")
	private Timestamp createdAt;

	@Column(name = "updated_by")
	private String updatedBy;

	@Column(name = "updated_at")
	private Timestamp updatedAt;

	@Column(name = "version")
	private Long version;
=======
	public String createdBy;

	@Column(name = "created_at")
	public Timestamp createdAt;

	@Column(name = "updated_by")
	public String updatedBy;

	@Column(name = "updated_at")
	public Timestamp updatedAt;

	@Column(name = "version")
	public Long version;

>>>>>>> b6d514d2fb14597636240c5bd37c26eeeea69139

	public Long getVersion() {
		return version;
	}

<<<<<<< HEAD
	public void setVersion(Long version) {
		if (version == null) {
			this.version = new Long(0);
		} else {
			this.version = version;
		}
	}

=======
>>>>>>> b6d514d2fb14597636240c5bd37c26eeeea69139
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public void setVersion(Long version) {
		
			this.version = version;
		
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
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
		this.updatedBy = updatedBy;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
}