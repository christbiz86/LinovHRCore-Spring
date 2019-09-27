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
@Table(name="core_cities", uniqueConstraints = @UniqueConstraint(columnNames = {"code", "province_id"}))
public class City extends BaseEntity{
	private static final long serialVersionUID = 1L;
	
    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @JoinColumn(name = "province_id", referencedColumnName = "id")
    @OneToOne
    private Province province;

    public String getName(){
        return name;
    }

    public void setName(String name){
    	if(name == null) {
    		this.name = new String();
    	} else {
    		this.name = name;
    	}
    }

    public String getCode(){
        return code;
    }

    public void setCode(String code){
    	if(code == null) {
    		this.code = new String();
    	} else {
    		this.code = code;
    	}
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

}
