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
@Table(name = "core_modules",uniqueConstraints = @UniqueConstraint(columnNames = {"code"}))
public class Module extends BaseEntity{
	private static final long serialVersionUID = 1L;

	@JoinColumn(name = "application_id", referencedColumnName = "id")
    @OneToOne()
    private Application application;
	
	@Column(name = "code")
    private String code;

	@Column(name = "name")
    private String name;
	
	@Column(name = "icon")
    private String icon;

	@Column(name = "sort_order")
    private Integer sortOrder;

	@Column(name = "path")
    private String path;

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		if(application==null) {
			this.application = new Application();
		}else {
			this.application = application;			
		}
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		if(code==null) {
			this.code = new String();
		}else {
			this.code = code;			
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(name==null) {
			this.name = new String();
		}else {
			this.name = name;			
		}
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		if(icon==null) {
			this.icon = new String();
		}else {
			this.icon = icon;			
		}
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		if(sortOrder==null) {
			this.sortOrder = 0;
		}else {
			this.sortOrder = sortOrder;			
		}
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		if(path==null) {
			this.path = new String();
		}else {
			this.path = path;			
		}
	}

}
