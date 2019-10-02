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
	
    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @JoinColumn(name = "country_id", referencedColumnName = "id")
    @OneToOne
    private Country country;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setCountryId(Country country) {
        this.country = country;
    }

    public Country getCountry() {
        return country;
    }
}
