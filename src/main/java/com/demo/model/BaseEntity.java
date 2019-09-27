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
	public String createdBy;

	@Column(name = "created_at")
	public Timestamp createdAt;

	@Column(name = "updated_by")
	public String updatedBy;

	@Column(name = "updated_at")
	public Timestamp updatedAt;

	@Column(name = "version")
	public Long version;
=======
	private String createdBy = "kosong";

	@Column(name = "created_at")
	private Timestamp createdAt = new Timestamp(System.currentTimeMillis());

	@Column(name = "updated_by")
	private String updatedBy = "kosong";

	@Column(name = "updated_at")
	private Timestamp updatedAt = new Timestamp(System.currentTimeMillis());

	@Column(name = "version")
	private Long version = new Long(0);
>>>>>>> 7190d508f8370056a00885410b81daec568d7d9d

	public Long getVersion() {
		return version;
	}

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