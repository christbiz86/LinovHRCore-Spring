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
@Table(name = "core_data_access_uni")
public class DataAccessUni extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	@JoinColumn(name = "company_id", referencedColumnName = "id")
	@OneToOne
	private Company company;
	
	@JoinColumn(name = "role_id", referencedColumnName = "id")
	@OneToOne
	private Role role;
	
	@Column(name = "menu_code")
	private String menuCode;
	
	@Column(name = "data_access_value")
	private String dataAccValue;
	
	@Column(name = "privilege")
	private String privilege;

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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		if (role == null) {
			this.role = new Role();
		} else {
			this.role = role;	
		}
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		if (menuCode == null) {
			this.menuCode = new String();
		} else {
			this.menuCode = menuCode;
		}
	}

	public String getDataAccValue() {
		return dataAccValue;
	}

	public void setDataAccValue(String dataAccValue) {
		if (dataAccValue == null) {
			this.dataAccValue = new String();
		} else {
			this.dataAccValue = dataAccValue;	
		}
	}

	public String getPrivilege() {
		return privilege;
	}

	public void setPrivilege(String privilege) {
		if (privilege == null) {
			this.privilege = new String();
		} else {
			this.privilege = privilege;
		}
	}
}
