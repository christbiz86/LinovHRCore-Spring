package com.demo.model;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "core_widget_type",uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class WidgetType extends BaseEntity {
	private static final long serialVersionUID = 1L;
			
	@Column(name = "name")
    private String name;
	
	@Column(name = "col_size")
    private Integer colSize;

	@Column(name = "row_size")
    private Integer rowSize;
	
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

	public Integer getColSize() {
		return colSize;
	}

	public void setColSize(Integer colSize) {
		if(colSize==null) {
			this.colSize = 0;
		}else {
			this.colSize = colSize;
		}
	}

	public Integer getRowSize() {
		return rowSize;
	}

	public void setRowSize(Integer rowSize) {
		if(rowSize==null) {
			this.rowSize=0;
		}else {
			this.rowSize = rowSize;	
		}
	}

}
