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
@Table(name = "core_role_menus",uniqueConstraints = @UniqueConstraint(columnNames = {"",""}))
public class RoleMenu extends BaseEntity{
	private static final long serialVersionUID = 1L;

	@JoinColumn(name = "tenant_id", referencedColumnName = "id")
    @OneToOne()
    private Tenant tenant;

	@JoinColumn(name = "role_id", referencedColumnName = "id")
    @OneToOne()
    private Role role;

	@JoinColumn(name = "menu_action_id", referencedColumnName = "id")
    @OneToOne()
    private MenuAction menuAction;
	
	@JoinColumn(name = "menu_id", referencedColumnName = "id")
    @OneToOne()
    private Menu menu;

	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		if(tenant==null) {
			this.tenant = new Tenant();
		}else {
			this.tenant = tenant;			
		}
	}

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

	public MenuAction getMenuAction() {
		return menuAction;
	}

	public void setMenuAction(MenuAction menuAction) {
		if(menuAction==null) {
			this.menuAction = new MenuAction();
		}else {
			this.menuAction = menuAction;			
		}
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		if(menu==null) {
			this.menu = new Menu();
		}else {
			this.menu = menu;			
		}
	}
}