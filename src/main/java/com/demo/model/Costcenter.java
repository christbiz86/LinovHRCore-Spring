package com.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="core_cost_centers")
public class Costcenter extends BaseEntity{
	
    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @OneToOne
    @JoinColumn(name="company_id", referencedColumnName = "id")
    private Company company;

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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
