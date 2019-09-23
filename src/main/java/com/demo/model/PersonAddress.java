package com.demo.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name="core_person_addresses",uniqueConstraints = @UniqueConstraint(columnNames = {"person_id","address"}))
public class PersonAddress implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

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

	@Column(name = "created_by")
    private String createdBy;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+7")
    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_by")
    private String updatedBy;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+7")
    @Column(name = "updated_at")
    private Timestamp updatedAt;
    
	@Column(name="is_default")
	private Boolean isDefault;
	
    @Column(name = "version")
    private Long version;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
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

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		if(version == null) {
			this.version = new Long(0);
    	} else {
    		this.version = version;
    	}
	}
	
}
