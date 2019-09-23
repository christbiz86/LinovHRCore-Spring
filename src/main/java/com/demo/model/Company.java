package com.demo.model;

import java.util.List;
import java.sql.Timestamp;

<<<<<<< HEAD
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonFormat;
=======
import java.sql.Timestamp;
import java.util.List;
>>>>>>> 24521ffb1ab2274485cc5393175ffd2fb4af96b8

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="core_companies")
public class Company {

	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "company",fetch = FetchType.EAGER)
    private List<Costcenter> costcenters;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+7")
    @Column(name = "updated_at")
    private Timestamp updatedAt;

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

    public String getCreatedBy(){
        return createdBy;
    }

    public void setCreatedBy(){
        this.createdBy = createdBy;
    }

    public String getUpdatedBy(){
        return updatedBy;
    }

    public void setUpdatedBy(){
        this.updatedBy = updatedBy;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

}
