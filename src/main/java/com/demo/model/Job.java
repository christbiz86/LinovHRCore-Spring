package com.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "core_jobs")
public class Job {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "created_by")
    private Integer createdBy;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+7")
    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_by")
    private Integer updatedBy;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+7")
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+7")
    @Column(name = "eff_begin")
    private Timestamp effBegin;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+7")
    @Column(name = "eff_end")
    private Timestamp effEnd;

    @JsonIgnoreProperties(value = {"jobs"})
    @ManyToOne(optional = false,fetch = FetchType.EAGER)
    @JoinColumn(name="company_id", referencedColumnName = "id")
    private Company company;

    public UUID getId(){
        return id;
    }

    public void setId(UUID id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(){
        this.name = name;
    }

    public String getCode(){
        return code;
    }

    public void setCode(){
        this.code = code;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
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

    public Company getCompany() {
        return company;
    }

    public void setCompany() {
        this.company = company;
    }

}
