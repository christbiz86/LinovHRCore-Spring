package com.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_model")
public class Model {
	
	public Model() {
		id = "";
		namaModel = "";
	}
	
	@Id
	@Column(name = "id")
//	@GeneratedValue(generator = "UUID")
//	@GenericGenerator(name="UUID", strategy="org.hibernate.id.UUIDGenerator")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	
	@Column(name = "nama_model")
	private String namaModel;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNamaModel() {
		return namaModel;
	}

	public void setNamaModel(String namaModel) {
		this.namaModel = namaModel;
	}
	
}
