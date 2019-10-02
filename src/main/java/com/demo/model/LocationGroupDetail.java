package com.demo.model;

import java.io.Serializable;

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

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "core_location_group_details", uniqueConstraints = @UniqueConstraint(columnNames = {"location_group_id", "location_id"}))
public class LocationGroupDetail implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	
	@JoinColumn(name = "location_id", referencedColumnName = "id")
	@OneToOne
	private Location location;
	
	@JoinColumn(name = "location_group_id", referencedColumnName = "id")
	@OneToOne
	private LocationGroup locationGroup;
	
	@Column(name = "version")
	private Long version = 0L;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		if(location == null) {
			this.location = new Location();
		} else {
			this.location = location;
		}
	}

	public LocationGroup getLocationGroup() {
		return locationGroup;
	}

	public void setLocationGroup(LocationGroup locationGroup) {
		if(locationGroup == null) {
			this.locationGroup = new LocationGroup();
		} else {
			this.locationGroup = locationGroup;
		}
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
}
