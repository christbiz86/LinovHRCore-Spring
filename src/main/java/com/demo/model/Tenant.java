package com.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="core_tenants")
public class Tenant {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name")
    private String name;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+7")
    @Column(name = "eff_begin")
    private Timestamp effBegin;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+7")
    @Column(name = "eff_end")
    private Timestamp effEnd;

    @OneToMany(mappedBy = "tenant",fetch = FetchType.LAZY)
    private List<Company> companies;

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


}
