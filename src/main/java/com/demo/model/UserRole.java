package com.demo.model;

import java.io.Serializable;
import java.sql.Timestamp;

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

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "core_user_roles",uniqueConstraints = @UniqueConstraint(columnNames = {"user_id","role_id"}))
public class UserRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;
	
	@JoinColumn(name = "user_id", referencedColumnName = "id")
    @OneToOne()
    private User user;

	@JoinColumn(name = "role_id", referencedColumnName = "id")
    @OneToOne()
    private Role role;
	
    @Column(name = "created_by")
    private String createdBy;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+7")
    @Column(name = "created_at")
    private Timestamp createdAt;

	@Column(name = "is_active")
    private Boolean isActive;
	
	@Column(name = "version")
    private Long version;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
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

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		if(version == null) {
			this.version = new Long(0);
    	} else {
    		this.version = version;
    	}
	}
	
}
