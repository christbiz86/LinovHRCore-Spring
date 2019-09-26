package com.demo.model;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name="core_role_applications",uniqueConstraints = @UniqueConstraint(columnNames = {"role_id","application_id"}))
public class RoleApplication extends BaseEntity{
	private static final long serialVersionUID = 1L;
	
	@JoinColumn(name = "role_id", referencedColumnName = "id")
	@OneToOne
	private Role role;

	@JoinColumn(name = "application_id", referencedColumnName = "id")
	@OneToOne
	private Application application;

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		if(role==null) {
			this.role = new Role();			
		}else {
			this.role = role;
		}
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		if(application==null) {
			this.application = new Application();
		}else {
			this.application = application;			
		}
	}
	
}
