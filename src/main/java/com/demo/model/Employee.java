package com.demo.model;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CacheConcurrencyStrategy;

//@Entity
//@Cacheable
//@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
//@Table(name="core_employees",uniqueConstraints = @UniqueConstraint(columnNames = {"person_id","assignment_id"}))
public class Employee extends BaseEntity{
	private static final long serialVersionUID = 1L;

}
