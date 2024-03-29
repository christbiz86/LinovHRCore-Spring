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
@Table(name = "core_applications",uniqueConstraints = @UniqueConstraint(columnNames = {"code"}))
public class Application extends BaseEntity {
	private static final long serialVersionUID = 1L;
		
	@Column(name = "code")
    private String code;

	@Column(name = "name")
    private String name;
	
	@Column(name = "user_access")
    private Boolean userAccess;

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

	public Boolean getUserAccess() {
		return userAccess;
	}

	public void setUserAccess(Boolean userAccess) {
		if(userAccess==null) {
			this.userAccess = true;
		}else {
			this.userAccess = userAccess;			
		}
	}

}
