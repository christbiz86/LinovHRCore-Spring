package com.demo.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity
//@Table(name = "core_units")
public class Unit {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	
	private String code;
	
	private String name;
	
	private Integer createdBy;
	
	private Timestamp createdAt;
	
	private Integer updatedBy;
	
	private Timestamp updatedAt;
	
	private Company company;
	
	private UnitType unitType;
	
	private Costcenter costCenter;
}
