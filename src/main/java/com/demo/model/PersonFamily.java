package com.demo.model;

import java.sql.Date;

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
		name = "core_person_families", 
		uniqueConstraints = @UniqueConstraint(
				columnNames = {"person_id", "lov_famr", "name"}
				)
		)
public class PersonFamily extends BaseEntity {
	public static final long serialVersionUID = 1L;
	
	@OneToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;
	
	@Column(name = "name")
    private String name;
	
	@OneToOne
    @JoinColumn(name = "lov_famr", referencedColumnName = "id")
    private Lov lovFamr;
	
	@OneToOne
    @JoinColumn(name = "lov_gndr", referencedColumnName = "id")
    private Lov lovGndr;
	
	@OneToOne
    @JoinColumn(name = "lov_edul", referencedColumnName = "id")
    private Lov lovEdul;
	
	@Column(name = "birth_date")
    private Date birthDate;
	
	@Column(name = "occupation")
    private String occupation;
	
	@Column(name = "description")
    private String description;
	
	@Column(name = "is_emergency")
    private Boolean isEmergency;
	
	@Column(name = "address")
    private String address;
	
	@Column(name = "phone")
    private String phone;

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Lov getLovFamr() {
		return lovFamr;
	}

	public void setLovFamr(Lov lovFamr) {
		this.lovFamr = lovFamr;
	}

	public Lov getLovGndr() {
		return lovGndr;
	}

	public void setLovGndr(Lov lovGndr) {
		this.lovGndr = lovGndr;
	}

	public Lov getLovEdul() {
		return lovEdul;
	}

	public void setLovEdul(Lov lovEdul) {
		this.lovEdul = lovEdul;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getIsEmergency() {
		return isEmergency;
	}

	public void setIsEmergency(Boolean isEmergency) {
		this.isEmergency = isEmergency;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
