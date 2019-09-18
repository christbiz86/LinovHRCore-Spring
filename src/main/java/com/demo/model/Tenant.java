package com.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name="core_tenants")
public class Tenant {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name="UUID", strategy="org.hibernate.id.UUIDGenerator")
    private String id;

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

    public String getId(){
        return id;
    }

    public void setId(String id){
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
