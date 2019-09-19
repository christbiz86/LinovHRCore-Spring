package com.demo.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;
import java.util.List;

@Entity
@Table(name="core_companies")
public class Company {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(name = "name")
    private String name;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+7")
    @Column(name = "eff_begin")
    private Timestamp effBegin;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+7")
    @Column(name = "eff_end")
    private Timestamp effEnd;

    @OneToMany(mappedBy = "company",fetch = FetchType.EAGER)
    private List<Costcenter> costcenters;

    @JsonIgnoreProperties(value = {"companies"})
    @ManyToOne(optional = false,fetch = FetchType.EAGER)
    @JoinColumn(name="tenant_id", referencedColumnName = "id")
    private Tenant tenant;

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Timestamp getEffBegin() {
        return effBegin;
    }

    public void setEffBegin(Timestamp effBegin) {
        this.effBegin = effBegin;
    }

    public Timestamp getEffEnd() {
        return effEnd;
    }

    public void setEffEnd(Timestamp effEnd) {
        this.effEnd = effEnd;
    }

    public List<Costcenter> getCostcenters() {
        return costcenters;
    }

    public void setCostcenters(List<Costcenter> costcenters) {
        this.costcenters = costcenters;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

}
