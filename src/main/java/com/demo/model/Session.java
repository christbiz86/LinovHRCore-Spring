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

import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "core_sessions")
public class Session implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	
	@JoinColumn(name = "tenant_id", referencedColumnName = "id")
	@OneToOne
	private Tenant tenant; 
	
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	@OneToOne
	private User user; 

	@Column(name = "user_agent")
	private String userAgent;
	
	@Column(name = "ip_address")
	private String ipAddress;
	
	@Column(name = "login_time")
	private Integer loginTime;
	
	@Column(name = "logout_time")
	private Integer logoutTime;
	
	@Column(name = "last_used_token")
	private String lastUsedToken;
	
	@Column(name = "is_blocked")
	private Boolean isBlocked;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		if(tenant==null) {
			this.tenant=new Tenant();
		}else {
			this.tenant = tenant;
		}
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

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		if(userAgent==null) {
			this.userAgent=new String();
		}else {
			this.userAgent = userAgent;
		}
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		if(ipAddress==null) {
			this.ipAddress=new String();
		}else {
			this.ipAddress = ipAddress;
		}
	}

	public Integer getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Integer loginTime) {
		if(loginTime==null) {
			this.loginTime=0;
		}else {
			this.loginTime = loginTime;
		}
	}

	public Integer getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(Integer logoutTime) {
		if(logoutTime==null) {
			this.logoutTime=0;
		}else {
			this.logoutTime = logoutTime;
		}
	}

	public String getLastUsedToken() {
		return lastUsedToken;
	}

	public void setLastUsedToken(String lastUsedToken) {
		if(lastUsedToken==null) {
			this.lastUsedToken=new String();
		}else {
			this.lastUsedToken = lastUsedToken;
		}
	}

	public Boolean getIsBlocked() {
		return isBlocked;
	}

	public void setIsBlocked(Boolean isBlocked) {
		if(isBlocked==null) {
			this.isBlocked=false;
		}else {
			this.isBlocked = isBlocked;
		}
	}
	
}
