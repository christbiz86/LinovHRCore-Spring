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
@Table(name = "core_widget",uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class Widget implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

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

	@Column(name = "version")
    private Integer version;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAppCode() {
		return appCode;
	}

	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	public WidgetType getWidgetType() {
		return widgetType;
	}

	public void setWidgetType(WidgetType widgetType) {
		this.widgetType = widgetType;
	}

	public String getParamIn() {
		return paramIn;
	}

	public void setParamIn(String paramIn) {
		this.paramIn = paramIn;
	}

	public String getParamOut() {
		return paramOut;
	}

	public void setParamOut(String paramOut) {
		this.paramOut = paramOut;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}
