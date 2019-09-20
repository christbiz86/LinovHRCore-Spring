package com.demo.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "core_lovs")
public class Lov{

	@Id
    @Column(name = "id")
    @GenericGenerator(name="UUID", strategy="org.hibernate.id.UUIDGenerator")
	private String id;

    @JoinColumn(name = "lov_type_id", referencedColumnName = "id")
    @OneToOne()
    private LovType lovType;
    
    @Column(name = "key_data")
    private String keyData;

    @Column(name = "val_data")
    private String valData;
    
    @Column(name = "created_by")
    private String createdBy;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+7")
    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_by")
    private String updatedBy;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+7")
    @Column(name = "updated_at")
    private Timestamp updatedAt;
    
    @Column(name = "is_disableable")
    private Boolean isDisableable;
    
    @Column(name = "is_active")
    private Boolean isActive;
    
    @Column(name = "arg1")
    private String arg1;
    
    @Column(name = "version")
    private Long version;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LovType getLovType() {
		return lovType;
	}

	public void setLovType(LovType lovType) {
		this.lovType = lovType;
	}

	public String getKeyData() {
		return keyData;
	}

	public void setKeyData(String keyData) {
		this.keyData = keyData;
	}

	public String getValData() {
		return valData;
	}

	public void setValData(String valData) {
		this.valData = valData;
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

	public Boolean getIsDisableable() {
		return isDisableable;
	}

	public void setIsDisableable(Boolean isDisableable) {
		this.isDisableable = isDisableable;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getArg1() {
		return arg1;
	}

	public void setArg1(String arg1) {
		this.arg1 = arg1;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
	
}
