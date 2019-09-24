package com.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="core_cost_centers")
public class Costcenter extends BaseEntity{

//    @Id
//    @Column(name = "id")
//    @GenericGenerator(name="UUID", strategy="org.hibernate.id.UUIDGenerator")
//    private String id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

//    @Column(name = "created_by")
//    private Integer createdBy;
//
//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+7")
//    @Column(name = "created_at")
//    private Timestamp createdAt;
//
//    @Column(name = "updated_by")
//    private Integer updatedBy;
//
//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+7")
//    @Column(name = "updated_at")
//    private Timestamp updatedAt;

    @JsonIgnoreProperties(value = {"costcenters"})
    @OneToOne
    @JoinColumn(name="company_id", referencedColumnName = "id")
    private Company company;

//    public String getId(){
//        return id;
//    }
//
//    public void setId(String id){
//        this.id = id;
//    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getCode(){
        return code;
    }

    public void setCode(String code){
        this.code = code;
    }

//    public Timestamp getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(Timestamp createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public Timestamp getUpdatedAt() {
//        return updatedAt;
//    }
//
//    public void setUpdatedAt(Timestamp updatedAt) {
//        this.updatedAt = updatedAt;
//    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
