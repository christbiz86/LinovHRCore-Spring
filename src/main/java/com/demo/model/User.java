package com.demo.model;

import java.sql.Timestamp;

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
@Table(name = "core_users",uniqueConstraints = @UniqueConstraint(columnNames = {"tenant_id","username"}))
public class User extends BaseEntity {
	private static final long serialVersionUID = 1L;
		
	@JoinColumn(name = "tenant_id", referencedColumnName = "id")
    @OneToOne()
    private Tenant tenant;
	
	@Column(name = "email")
    private String email;
	
	@Column(name = "password")
    private String password;
	
	@Column(name = "is_deleted")
    private Boolean isDeleted;
	    
	@Column(name = "username")
    private String username;
	
	@Column(name = "is_sa")
    private Boolean isSa;
	
	@JoinColumn(name = "person_id", referencedColumnName = "id")
    @OneToOne()
    private Person person;
	
	@Column(name = "url_forgot_password")
    private String urlForgotPassword;
	
	@Column(name = "change_password")
    private Boolean changePassword;
	
	@Column(name = "active_link")
    private Timestamp activeLink;
	
	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		if(tenant == null) {
			this.tenant = new Tenant();
    	} else {
    		this.tenant = tenant;
    	}

	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if(email==null) {
			this.email = new String();
		}else {
			this.email = email;
		}
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if(password==null) {
			this.password=new String();
		}else {
			this.password = password;
		}
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		if(isDeleted) {
			this.isDeleted=false;
		}else {
			this.isDeleted = isDeleted;
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		if(username==null) {
			this.username=new String();
		}else {
			this.username = username;
		}
	}

	public Boolean getIsSa() {
		return isSa;
	}

	public void setIsSa(Boolean isSa) {
		if(isSa==null) {
			this.isSa=false;
		}else {
			this.isSa = isSa;
		}
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		if(person==null) {
			this.person=new Person();
		}else {
			this.person = person;
		}
	}

	public String getUrlForgotPassword() {
		return urlForgotPassword;
	}

	public void setUrlForgotPassword(String urlForgotPassword) {
		if(urlForgotPassword==null) {
			this.urlForgotPassword=new String();
		}else {
			this.urlForgotPassword = urlForgotPassword;
		}
	}

	public Boolean getChangePassword() {
		return changePassword;
	}

	public void setChangePassword(Boolean changePassword) {
		if(changePassword==null) {
			this.changePassword=false;
		}else {
			this.changePassword = changePassword;
		}
	}

	public Timestamp getActiveLink() {
		return activeLink;
	}

	public void setActiveLink(Timestamp activeLink) {
		this.activeLink = activeLink;
	}

}
