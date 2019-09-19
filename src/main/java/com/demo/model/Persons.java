package com.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "core_persons")
public class Persons {
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
	
	@JoinColumn(name = "tenant_id", referencedColumnName = "id")
	@OneToOne
	private Tenant tenant; 
	
	@Column(name = "id_card")
	private String idCard;
	
	
}
