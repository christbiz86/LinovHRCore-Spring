package com.demo.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="core_tenants")
public class Tenant extends BaseEntity {

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "tenant",fetch = FetchType.LAZY)
    private List<Company> companies;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}
