package com.demo.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "core_persons")
public class Person {
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
	
	@JoinColumn(name = "tenant_id", referencedColumnName = "id")
	@OneToOne
	private Tenant tenant; 
	
	@Column(name = "id_card")
	private String idCard;
	
	@Column(name = "eff_begin")
	private Date effBegin;
	
	@Column(name = "eff_end")
	private Date effEnd;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "birth_place")
	private String birthPlace;
	
	@Column(name = "birth_date")
	private Date birthDate;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "mobile")
	private String mobile;
	
	@Column(name = "social_media")
	private String socialMedia;
	
	@Column(name = "hobbies")
	private String hobbies;
	
	@Column(name = "strength")
	private String strength;
	
	@Column(name = "weakness")
	private String weakness;
	
	@JoinColumn(name = "country_id", referencedColumnName = "id")
	@OneToOne
	private Country country;
	
	@JoinColumn(name = "lov_ptyp", referencedColumnName = "id")
	@OneToOne
	private Lov lovPtyp;
	
	@JoinColumn(name = "lov_blod", referencedColumnName = "id")
	@OneToOne
	private Lov lovBlod;
	
	@JoinColumn(name = "lov_gndr", referencedColumnName = "id")
	@OneToOne
	private Lov lovGndr;
	
	@JoinColumn(name = "lov_rlgn", referencedColumnName = "id")
	@OneToOne
	private Lov lovRlgn;
	
	@JoinColumn(name = "lov_mars", referencedColumnName = "id")
	@OneToOne
	private Lov lovMars;
	
	@Column(name = "created_by")
	private Integer createdBy;
	
	@Column(name = "created_at")
	private Timestamp createdAt;
	
	@Column(name = "updated_by")
	private Integer updatedBy;
	
	@Column(name = "updated_at")
	private Timestamp updatedAt;
	
	@Column(name = "file_foto")
	private String fileFoto;
	
	@Column(name = "vacancy_id")
	private Integer vacancyId;
	
	@Column(name = "candidate_ready_to_hire_id")
	private Integer candidateReadyToHireId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public Date getEffBegin() {
		return effBegin;
	}

	public void setEffBegin(Date effBegin) {
		this.effBegin = effBegin;
	}

	public Date getEffEnd() {
		return effEnd;
	}

	public void setEffEnd(Date effEnd) {
		this.effEnd = effEnd;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getSocialMedia() {
		return socialMedia;
	}

	public void setSocialMedia(String socialMedia) {
		this.socialMedia = socialMedia;
	}

	public String getHobbies() {
		return hobbies;
	}

	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}

	public String getStrength() {
		return strength;
	}

	public void setStrength(String strength) {
		this.strength = strength;
	}

	public String getWeakness() {
		return weakness;
	}

	public void setWeakness(String weakness) {
		this.weakness = weakness;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Lov getLovPtyp() {
		return lovPtyp;
	}

	public void setLovPtyp(Lov lovPtyp) {
		this.lovPtyp = lovPtyp;
	}

	public Lov getLovBlod() {
		return lovBlod;
	}

	public void setLovBlod(Lov lovBlod) {
		this.lovBlod = lovBlod;
	}

	public Lov getLovGndr() {
		return lovGndr;
	}

	public void setLovGndr(Lov lovGndr) {
		this.lovGndr = lovGndr;
	}

	public Lov getLovRlgn() {
		return lovRlgn;
	}

	public void setLovRlgn(Lov lovRlgn) {
		this.lovRlgn = lovRlgn;
	}

	public Lov getLovMars() {
		return lovMars;
	}

	public void setLovMars(Lov lovMars) {
		this.lovMars = lovMars;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getFileFoto() {
		return fileFoto;
	}

	public void setFileFoto(String fileFoto) {
		this.fileFoto = fileFoto;
	}

	public Integer getVacancyId() {
		return vacancyId;
	}

	public void setVacancyId(Integer vacancyId) {
		this.vacancyId = vacancyId;
	}

	public Integer getCandidateReadyToHireId() {
		return candidateReadyToHireId;
	}

	public void setCandidateReadyToHireId(Integer candidateReadyToHireId) {
		this.candidateReadyToHireId = candidateReadyToHireId;
	}
	
}
