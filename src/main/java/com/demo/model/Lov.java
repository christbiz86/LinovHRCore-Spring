package com.demo.model;

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
@Table(name = "core_lovs",uniqueConstraints = @UniqueConstraint(columnNames = {"lov_type_id","key_data"}))
public class Lov extends BaseEntity{

    @JoinColumn(name = "lov_type_id", referencedColumnName = "id")
    @OneToOne()
    private LovType lovType;
    
    @Column(name = "key_data")
    private String keyData;

    @Column(name = "val_data")
    private String valData;
    
    @Column(name = "is_disableable")
    private Boolean isDisableable;
    
    @Column(name = "is_active")
    private Boolean isActive;
    
    @Column(name = "arg1")
    private String arg1;
    
	public LovType getLovType() {
		return lovType;
	}

	public void setLovType(LovType lovType) {
		if(lovType==null) {
			this.lovType = new LovType();
		}else {
			this.lovType = lovType;			
		}
	}

	public String getKeyData() {
		return keyData;
	}

	public void setKeyData(String keyData) {
		if(keyData==null) {
			this.keyData = new String();
		}else {
			this.keyData = keyData;			
		}
	}

	public String getValData() {
		return valData;
	}

	public void setValData(String valData) {
		if(valData==null) {
			this.valData = new String();
		}else {
			this.valData = valData;			
		}
	}

	public Boolean getIsDisableable() {
		return isDisableable;
	}

	public void setIsDisableable(Boolean isDisableable) {
		if(isDisableable==null) {
			this.isDisableable = false;
		}else {
			this.isDisableable = isDisableable;			
		}
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		if(isActive==null) {
			this.isActive = true;
		}else {
			this.isActive = isActive;			
		}
	}

	public String getArg1() {
		return arg1;
	}

	public void setArg1(String arg1) {
		if(arg1==null) {
			this.arg1 = new String();
		}else {
			this.arg1 = arg1;
		}
	}

}
