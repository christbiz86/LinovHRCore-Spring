package com.demo.model;

import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "compt_rating_scales", uniqueConstraints = @UniqueConstraint(columnNames = { "company_id", "code" }))
public class RatingScale extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@JoinColumn(name = "company_id", referencedColumnName = "id")
	@OneToOne
	private Company company;

	@Column(name = "code")
	private String code;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "ratingScale", fetch = FetchType.LAZY)
	private List<RatingScaleDetail> ratingScDet;

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		if (company == null) {
			this.company = new Company();
		} else {
			this.company = company;
		}
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		if (code == null) {
			this.code = new String();
		} else {
			this.code = code;	
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name == null) {
			this.name = new String();
		} else {
			this.name = name;	
		}
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		if (description == null) {
			this.description = new String();
		} else {
			this.description = description;	
		}
	}

	public List<RatingScaleDetail> getRatingScDet() {
		return ratingScDet;
	}

	public void setRatingScDet(List<RatingScaleDetail> ratingScDet) {
		this.ratingScDet = ratingScDet;
	}
}
