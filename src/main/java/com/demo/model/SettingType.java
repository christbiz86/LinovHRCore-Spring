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
@Table(name="core_setting_types",uniqueConstraints = @UniqueConstraint(columnNames = {"code"}))
public class SettingType extends BaseEntity{
	private static final long serialVersionUID = 1L;

	@Column(name="code")
	private String code;
	
	@Column(name="name")
	private String name;
	
	@Column(name="type")
	private String type;
	
	@Column(name="vtype")
	private String vtype;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		if(code==null) {
			this.code = new String();
		}else {
			this.code = code;			
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(name==null) {
			this.name = new String();
		}else {
			this.name = name;			
		}
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		if(type==null) {
			this.type = new String();
		}else {
			this.type = type;			
		}
	}

	public String getVtype() {
		return vtype;
	}

	public void setVtype(String vtype) {
		if(vtype==null) {
			this.vtype = new String();
		}else {
			this.vtype=vtype;
		}
	}
}
