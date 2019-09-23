package com.demo.model;

import java.sql.Timestamp;

@Entity
@Table(name="core_cost_centers")
public class Costcenter {

    @Id
    @Column(name = "id")
    @GenericGenerator(name="UUID", strategy="org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+7")
    @Column(name = "eff_begin")
    private Timestamp effBegin;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+7")
    @Column(name = "eff_end")
    private Timestamp effEnd;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_by")
    private String updatedBy;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+7")
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @JsonIgnoreProperties(value = {"costcenters"})
    @ManyToOne(optional = false,fetch = FetchType.EAGER)
    @JoinColumn(name="company_id", referencedColumnName = "id")
    private Company company;

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

    public String getCode(){
        return code;
    }

    public void setCode(String code){
        this.code = code;
    }

    public String getCreatedBy(){
        return createdBy;
    }

    public void setCreatedBy(){ this.createdBy = createdBy;
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
