package com.demo.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "core_lovs")
public class Lov {

	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
	private String id;

    @JsonIgnoreProperties(value = {"lovs"})
    @JoinColumn(name = "lov_type_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private LovType lovType;
    
    @Column(name = "key_data")
    private String keyData;

    @Column(name = "val_data")
    private String valData;
    
    @Column(name = "created_by")
    private Integer createdBy;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+7")
    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_by")
    private Integer updatedBy;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+7")
    @Column(name = "updated_at")
    private Timestamp updatedAt;
    
    @Column(name = "is_disableable")
    private String isDisableable;
    
    @Column(name = "is_active")
    private String isActive;
    
    @Column(name = "arg1")
    private String arg1;

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

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getIsDisableable() {
		return isDisableable;
	}

	public void setIsDisableable(String isDisableable) {
		this.isDisableable = isDisableable;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getArg1() {
		return arg1;
	}

	public void setArg1(String arg1) {
		this.arg1 = arg1;
	}
    
}
