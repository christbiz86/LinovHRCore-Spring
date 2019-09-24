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
@Table(name = "core_user_role_widget",uniqueConstraints = @UniqueConstraint(columnNames = {"user_role_id","widget_id"}))
public class UserRoleWidget implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;
	
	@JoinColumn(name = "user_role_id", referencedColumnName = "id")
    @OneToOne()
    private UserRole userRole;

	@JoinColumn(name = "widget_id", referencedColumnName = "id")
    @OneToOne()
    private Widget widget;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public Widget getWidget() {
		return widget;
	}

	public void setWidget(Widget widget) {
		this.widget = widget;
	}
	
}
