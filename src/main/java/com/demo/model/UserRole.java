package com.demo.model;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "core_user_roles",uniqueConstraints = @UniqueConstraint(columnNames = {"user_id","role_id"}))
public class UserRole extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	@JoinColumn(name = "user_id", referencedColumnName = "id")
    @OneToOne()
    private User user;

	@JoinColumn(name = "role_id", referencedColumnName = "id")
    @OneToOne()
    private Role role;
	
	@Column(name = "is_active")
    private Boolean isActive;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		if(user==null) {
			this.user=new User();
		}else {
			this.user = user;	
		}
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		if(role==null) {
			this.role=new Role();
		}else {
			this.role = role;			
		}
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		if(isActive==null) {
			this.isActive = false;
		}else {
			this.isActive = isActive;	
		}
	}
	
}
