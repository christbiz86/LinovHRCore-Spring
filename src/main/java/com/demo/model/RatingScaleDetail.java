package com.demo.model;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "core_rating_scale_details", uniqueConstraints = @UniqueConstraint(columnNames = {"rating_scale_id", "code"}))
public class RatingScaleDetail extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
    @JsonIgnoreProperties(value = {"ratingScDet"})
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "rating_scale_id", referencedColumnName = "id")
    private RatingScale ratingScale;
    
    @Column(name = "level")
    private Integer level;
    
	@Column(name = "code")
	private String code;
	
	@Column(name = "label")
	private String label;

	public RatingScale getRatingScale() {
		return ratingScale;
	}

	public void setRatingScale(RatingScale ratingScale) {
		this.ratingScale = ratingScale;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
