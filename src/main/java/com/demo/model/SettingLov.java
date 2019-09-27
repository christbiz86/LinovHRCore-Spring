package com.demo.model;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name="core_setting_lovs",uniqueConstraints = @UniqueConstraint(columnNames = {"setting_type_id","key_data"}))
public class SettingLov extends BaseEntity{
	private static final long serialVersionUID = 1L;

	@JoinColumn(name = "setting_type_id", referencedColumnName = "id")
	@OneToOne
	private SettingType settingType;
	
	@Column(name="key_data")
	private String keyData;
	
	@Column(name="val_data")
	private String valData;

	public SettingType getSettingType() {
		return settingType;
	}

	public void setSettingType(SettingType settingType) {
		if(settingType==null) {
			this.settingType = new SettingType();
		}else {
			this.settingType = settingType;			
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
}
