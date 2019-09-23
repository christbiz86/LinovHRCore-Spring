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
@Table(name = "core_users",uniqueConstraints = @UniqueConstraint(columnNames = {"tenant_id","username"}))
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;
	
	@JoinColumn(name = "tenant_id", referencedColumnName = "id")
    @OneToOne()
    private Tenant tenant;
	
	@Column(name = "email")
    private String email;
	
	@Column(name = "password")
    private String password;
	
	@Column(name = "is_deleted")
    private Boolean isDeleted;
	
    @Column(name = "created_by")
    private String createdBy;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+7")
    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_by")
    private String updatedBy;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+7")
    @Column(name = "updated_at")
    private Timestamp updatedAt;
    
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
	
	@Column(name = "version")
    private Long version;

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

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
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
