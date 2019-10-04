package com.demo.model;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(
		name = "core_grades", 
		uniqueConstraints = @UniqueConstraint(
				columnNames = {"company_id", "code"}
				)
		)
public class Grade extends BaseEntity {
	public static final long serialVersionUID = 1L;
	
	@OneToOne
    @JoinColumn(name="company_id", referencedColumnName = "id")
    private Company company;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "ordinal")
    private Integer ordinal;
    
    @Column(name = "work_month")
    private Integer workMonth;
    
    @Column(name = "bottom_rate")
	private Integer bottomRate;
	
	@Column(name = "mid_rate")
	private Integer midRate;
	
	@Column(name = "top_rate")
	private Integer topRate;
	
	@Column(name = "version")
	private Long version;

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

    public Integer getOrdinal(){
        return ordinal;
    }

    public void setOrdinal(Integer ordinal){
        this.ordinal = ordinal;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

	public Integer getWorkMonth() {
		return workMonth;
	}

	public void setWorkMonth(Integer workMonth) {
		this.workMonth = workMonth;
	}

	public Integer getBottomRate() {
		return bottomRate;
	}

	public void setBottomRate(Integer bottomRate) {
		this.bottomRate = bottomRate;
	}

	public Integer getMidRate() {
		return midRate;
	}

	public void setMidRate(Integer midRate) {
		this.midRate = midRate;
	}

	public Integer getTopRate() {
		return topRate;
	}

	public void setTopRate(Integer topRate) {
		this.topRate = topRate;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

}
