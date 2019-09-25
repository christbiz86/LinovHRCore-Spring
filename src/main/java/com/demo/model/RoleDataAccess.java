package com.demo.model;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "core_role_data_access")
public class RoleDataAccess extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@JoinColumn(name = "tenant_id", referencedColumnName = "id")
	@OneToOne
	private Tenant tenant;
	
	@JoinColumn(name = "role_id", referencedColumnName = "id")
	@OneToOne
	private Role role;
	
	@JoinColumn(name = "data_access_id", referencedColumnName = "id")
	@OneToOne
	private DataAccess dataAccess;
	
	@Column(name = "data_access_value")
	private String dataAccValue;
	
	@Column(name = "privilege")
	private String privilege;

	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public DataAccess getDataAccess() {
		return dataAccess;
	}

	public void setDataAccess(DataAccess dataAccess) {
		this.dataAccess = dataAccess;
	}

	public String getDataAccValue() {
		return dataAccValue;
	}

	public void setDataAccValue(String dataAccValue) {
		this.dataAccValue = dataAccValue;
	}

	public String getPrivilege() {
		return privilege;
	}

	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}
}
