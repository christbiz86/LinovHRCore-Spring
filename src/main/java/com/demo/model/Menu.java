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
@Table(name = "core_menus",uniqueConstraints = @UniqueConstraint(columnNames = {"code"}))
public class Menu extends BaseEntity{
	private static final long serialVersionUID = 1L;

	@JoinColumn(name = "module_id", referencedColumnName = "id")
    @OneToOne()
    private Module module;
	
	@Column(name = "code")
    private String code;

	@Column(name = "name")
    private String name;
	
	@Column(name = "sort_order")
    private Integer sortOrder;
	
	@Column(name = "menu_module_id")
    private String menuModuleId;
	
	@Column(name = "level")
    private Integer level;
	
	@Column(name = "parent_menu_id")
    private String parentMenuId;
	
	@Column(name = "path")
    private String path;

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		if(module==null) {
			this.module = new Module();
		}else {
			this.module = module;			
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

	public String getMenuModuleId() {
		return menuModuleId;
	}

	public void setMenuModuleId(String menuModuleId) {
		if(menuModuleId==null) {
			this.menuModuleId = new String();
		}else {
			this.menuModuleId = menuModuleId;	
		}
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		if(level==null) {
			this.level = 0;
		}else {
			this.level = level;	
		}
	}

	public String getParentMenuId() {
		return parentMenuId;
	}

	public void setParentMenuId(String parentMenuId) {
		if(parentMenuId==null) {
			this.parentMenuId = new String();
		}else {
			this.parentMenuId = parentMenuId;			
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
