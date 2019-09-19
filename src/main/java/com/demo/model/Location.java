package com.demo.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "core_locations")
public class Location {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "tax_office_code")
	private String taxOfficeCode;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "postal_code")
	private String postalCode;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "fax")
	private String fax;
	
	@Column(name = "created_by")
	private Integer createdBy;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+7")
	@Column(name = "created_at")
	private Timestamp createdAt;
	
	@Column(name = "updated_by")
	private Integer updatedBy;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+7")
	@Column(name = "updated_at")
	private Timestamp updatedAt;
	
	@Column(name = "code")
	private String code;
	
	@Column(name = "latitude")
	private Integer latitude;
	
	@Column(name = "longitude")
	private Integer longitude;
	
	@JoinColumn(name = "company_id", referencedColumnName = "id")
	@OneToOne
	private Company company;
}
