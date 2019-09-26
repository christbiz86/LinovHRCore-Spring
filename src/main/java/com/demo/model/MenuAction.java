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
@Table(name = "core_menu_actions",uniqueConstraints = @UniqueConstraint(columnNames = {"code"}))
public class MenuAction extends BaseEntity{
	private static final long serialVersionUID = 1L;

	@JoinColumn(name = "menu_id", referencedColumnName = "id")
    @OneToOne()
    private Menu menu;
	
	@Column(name = "code")
    private String code;
	
	@Column(name = "name")
    private String name;

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		if(menu==null) {
			this.menu = new Menu();
		}else {
			this.menu = menu;			
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
	
}
