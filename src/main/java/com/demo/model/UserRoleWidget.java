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
@Table(name = "core_user_role_widget",uniqueConstraints = @UniqueConstraint(columnNames = {"user_role_id","widget_id"}))
public class UserRoleWidget extends BaseEntity{
	private static final long serialVersionUID = 1L;
		
	@JoinColumn(name = "user_role_id", referencedColumnName = "id")
    @OneToOne()
    private UserRole userRole;

	@JoinColumn(name = "widget_id", referencedColumnName = "id")
    @OneToOne()
    private Widget widget;

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		if(userRole==null) {
			this.userRole = new UserRole();
		}else {
			this.userRole = userRole;			
		}
	}

	public Widget getWidget() {
		return widget;
	}

	public void setWidget(Widget widget) {
		if(widget==null) {
			this.widget = new Widget();
		}else {
			this.widget = widget;			
		}
	}
	
}
