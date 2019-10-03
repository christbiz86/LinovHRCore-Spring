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
@Table(name = "core_widget",uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class Widget extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	@Column(name = "name")
    private String name;
	
	@Column(name = "description")
    private String description;
	
	@Column(name = "app_code")
    private String appCode;
	
	@JoinColumn(name = "widget_type_id", referencedColumnName = "id")
    @OneToOne()
    private WidgetType widgetType;
	
	@Column(name = "param_in")
    private String paramIn;

	@Column(name = "param_out")
    private String paramOut;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		if(description==null) {
			this.description = new String();
		}else {
			this.description = description;			
		}
	}

	public String getAppCode() {
		return appCode;
	}

	public void setAppCode(String appCode) {
		if(appCode==null) {
			this.appCode = new String();
		}else {
			this.appCode = appCode;			
		}
	}

	public WidgetType getWidgetType() {
		return widgetType;
	}

	public void setWidgetType(WidgetType widgetType) {
		if(widgetType==null) {
			this.widgetType = new WidgetType();
		}else {
			this.widgetType = widgetType;			
		}
	}

	public String getParamIn() {
		return paramIn;
	}

	public void setParamIn(String paramIn) {
		if(paramIn==null) {
			this.paramIn = new String();
		}else {
			this.paramIn = paramIn;			
		}
	}

	public String getParamOut() {
		return paramOut;
	}

	public void setParamOut(String paramOut) {
		if(paramOut==null) {
			this.paramOut = new String();
		}else {
			this.paramOut = paramOut;			
		}
	}

}
