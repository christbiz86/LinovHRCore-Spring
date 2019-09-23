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
@Table(name = "core_locations", uniqueConstraints = @UniqueConstraint(columnNames = {"code", "company_id"}))
public class Location implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@JoinColumn(name = "tax_office_id", referencedColumnName = "id")
	@OneToOne
	private TaxOffice taxOffice;
	
	@Column(name = "calendar_id")
	private Integer calendar;
	
	@JoinColumn(name = "city_id", referencedColumnName = "id")
	@OneToOne
	private City city;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "postal_code")
	private String postalCode;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "fax")
	private String fax;
	
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
	
	@Column(name = "code")
	private String code;
	
	@Column(name = "latitude")
	private Integer latitude;
	
	@Column(name = "longitude")
	private Integer longitude;
	
	@Column(name = "version")
	private Long version;
	
	@JoinColumn(name = "company_id", referencedColumnName = "id")
	@OneToOne
	private Company company;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(name == null) {
			this.name = new String();
		} else {
			this.name = name;
		}
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TaxOffice getTaxOffice() {
		return taxOffice;
	}

	public void setTaxOffice(TaxOffice taxOffice) {
		this.taxOffice = taxOffice;
	}

	public String getAddress() {
		return address;
	}

	public Integer getCalendar() {
		return calendar;
	}

	public void setCalendar(Integer calendar) {
		this.calendar = calendar;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		if(city == null) {
			this.city = new City();
		} else {
			this.city = city;
		}
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		if(createdBy == null) {
			this.createdBy = new String();
		} else {
			this.createdBy = createdBy;
		}
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		if(code == null) {
			this.code = new String();
		} else {
			this.code = code;
		}
	}

	public Integer getLatitude() {
		return latitude;
	}

	public void setLatitude(Integer latitude) {
		this.latitude = latitude;
	}

	public Integer getLongitude() {
		return longitude;
	}

	public void setLongitude(Integer longitude) {
		this.longitude = longitude;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		if(company == null) {
			this.company = new Company();
		} else {
			this.company = company;
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
