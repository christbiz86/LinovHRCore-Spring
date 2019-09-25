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
		name = "core_countries", 
		uniqueConstraints = @UniqueConstraint(
				columnNames = {"code"}
				)
		)
public class Country extends BaseEntity {
	
    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "dial_code")
    private String dialCode;

    @Column(name = "nationality")
    private String nationality;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDialCode() {
		return dialCode;
	}

	public void setDialCode(String dialCode) {
		this.dialCode = dialCode;
	}

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

	public String getNationality() {
		return nationality;
	}

}
