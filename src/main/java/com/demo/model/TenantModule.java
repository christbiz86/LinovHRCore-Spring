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
@Table(
		name = "core_tenant_modules", 
		uniqueConstraints = @UniqueConstraint(
				columnNames = {"tenant_id", "module_id"}
				)
		)
public class TenantModule extends BaseEntity {
	
	@OneToOne
    @JoinColumn(name = "tenant_id", referencedColumnName = "id")
    private Tenant tenant;
	
	@OneToOne
    @JoinColumn(name = "module_id", referencedColumnName = "id")
    private Module module;

	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

}
