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
@Table(name="core_user_settings",uniqueConstraints = @UniqueConstraint(columnNames = {"user_id","setting_lov_id"}))
public class UserSetting extends BaseEntity{
	private static final long serialVersionUID = 1L;
	
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	@OneToOne
	private User user;
	
	@JoinColumn(name = "setting_lov_id", referencedColumnName = "id")
	@OneToOne
	private SettingLov settingLov;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		if(user==null) {
			this.user = new User();
		}else {
			this.user = user;			
		}
	}

	public SettingLov getSettingLov() {
		return settingLov;
	}

	public void setSettingLov(SettingLov settingLov) {
		if(settingLov==null) {
			this.settingLov = new SettingLov();
		}else {
			this.settingLov = settingLov;			
		}
	}
}
