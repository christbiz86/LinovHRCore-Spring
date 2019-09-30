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
@Table(
		name = "core_provinces", 
		uniqueConstraints = @UniqueConstraint(
				columnNames = {"country_id", "code"}
				)
		)
public class Province extends BaseEntity {
	public static final long serialVersionUID = 1L;
	
    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @JoinColumn(name = "country_id", referencedColumnName = "id")
    @OneToOne
    private Country country;
    
    public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }
    
    public void setCountry(Country country) {
		this.country = country;
	}
    
}
