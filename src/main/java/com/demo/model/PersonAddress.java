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
@Table(name="core_person_addresses",uniqueConstraints = @UniqueConstraint(columnNames = {"person_id","address"}))
public class PersonAddress extends BaseEntity{
	private static final long serialVersionUID = 1L;

	@JoinColumn(name = "person_id", referencedColumnName = "id")
    @OneToOne
    private Person person;
		
	@JoinColumn(name = "lov_rsty", referencedColumnName = "id")
	@OneToOne
	private Lov lovRsty;
	
	@JoinColumn(name = "lov_rsow", referencedColumnName = "id")
	@OneToOne
	private Lov lovRsow;
	
	@JoinColumn(name = "city_id", referencedColumnName = "id")
	@OneToOne
	private City city;
	
	@Column(name="address")
	private String address;
	
	@Column(name="postal_code")
	private String postalCode;
	
	@Column(name="phone")
	private String phone;

	@Column(name="fax")
	private String fax;

	@Column(name="map_location")
	private String mapLocation;
    
	@Column(name="is_default")
	private Boolean isDefault;
	
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		if(person==null) {
			this.person=new Person();
		}else {
			this.person = person;
		}
	}

	public Lov getLovRsty() {
		return lovRsty;
	}

	public void setLovRsty(Lov lovRsty) {
		if(lovRsty==null) {
			this.lovRsty=new Lov();
		}else {
			this.lovRsty = lovRsty;
		}
	}

	public Lov getLovRsow() {
		return lovRsow;
	}

	public void setLovRsow(Lov lovRsow) {
		if(lovRsow==null) {
			this.lovRsow=new Lov();
		}else {
			this.lovRsow = lovRsow;
		}
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		if(city==null) {
			this.city=new City();
		}else {
			this.city = city;
		}
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		if(address==null) {
			this.address=new String();
		}else {
			this.address = address;
		}
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		if(postalCode==null) {
			this.postalCode=new String();
		}else {
			this.postalCode = postalCode;
		}
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		if(phone==null) {
			this.phone=new String();
		}else {
			this.phone = phone;
		}
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		if(fax==null) {
			this.fax=new String();
		}else {
			this.fax = fax;
		}
	}

	public String getMapLocation() {
		return mapLocation;
	}

	public void setMapLocation(String mapLocation) {
		if(mapLocation==null) {
			this.mapLocation=new String();
		}else {
			this.mapLocation = mapLocation;
		}
	}

	public boolean isDefault() {
		return isDefault;
	}

	public void setDefault(Boolean isDefault) {
		if(isDefault==null) {
			this.isDefault=false;
		}else {
			this.isDefault = isDefault;
		}
	}
	
}
