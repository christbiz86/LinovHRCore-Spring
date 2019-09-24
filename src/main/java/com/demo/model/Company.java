package com.demo.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="core_companies")
public class Company extends BaseEntity{

    @Column(name = "name")
    private String name;

//    @OneToOne(mappedBy = "company",fetch = FetchType.EAGER)
//    private Costcenter costcenters;

    @JsonIgnoreProperties(value = {"companies"})
    @ManyToOne(optional = false,fetch = FetchType.EAGER)
    @JoinColumn(name="tenant_id", referencedColumnName = "id")
    private Tenant tenant;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

//    public Costcenter getCostcenters() {
//        return costcenters;
//    }
//
//    public void setCostcenters(Costcenter costcenters) {
//        this.costcenters = costcenters;
//    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

}
