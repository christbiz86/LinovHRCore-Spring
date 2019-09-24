package com.demo.model;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "core_lov_types",uniqueConstraints = @UniqueConstraint(columnNames = {"code"}))
public class LovType extends BaseEntity{
	private static final long serialVersionUID = 1L;

    @Column(name = "code")
    private String code;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "arg1")
    private String arg1;
    
    @Column(name = "version")
    private Long version;

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

