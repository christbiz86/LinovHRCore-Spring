package com.demo.model;

import java.util.Date;

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
@Table(name = "core_assignment",uniqueConstraints = @UniqueConstraint(columnNames = {"company_id","position_slot_id"}))
public class Assignment extends BaseEntity{
	private static final long serialVersionUID = 1L;

	@JoinColumn(name = "company_id", referencedColumnName = "id")
	@OneToOne
	private Company company;
	
	@JoinColumn(name = "employee_status_id", referencedColumnName = "id")
	@OneToOne
	private EmployeeStatus employeeStatus;
	
	@JoinColumn(name = "grade_id", referencedColumnName = "id")
	@OneToOne
	private Grade grade;
	
	@Column(name = "supervisor_id")
	private String supervisor;

	@JoinColumn(name = "assignment_reason_id", referencedColumnName = "id")
	@OneToOne
	private AssignmentReason assignmentReason;

	@JoinColumn(name = "position_slot_id", referencedColumnName = "id")
	@OneToOne
	private PositionSlot positionSlot;

	@Column(name="is_primary")
	private Boolean isPrimary;
	
	@Column(name="file_assignment_doc")
	private String fileAssignmentDoc;

	@Column(name="note")
	private String note;

	@Column(name="final_process_date")
	private Date finalProcessDate;
	
	@Column(name="assignment_doc_number")
	private String assignmentDocNumber;
	
	@JoinColumn(name = "lov_acty", referencedColumnName = "id")
	@OneToOne
	private Lov lovActy;

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		if(company==null) {
			this.company = new Company();
		}else {
			this.company = company;			
		}
	}

	public EmployeeStatus getEmployeeStatus() {
		return employeeStatus;
	}

	public void setEmployeeStatus(EmployeeStatus employeeStatus) {
		if(employeeStatus==null) {
			this.employeeStatus = new EmployeeStatus();
		}else {
			this.employeeStatus = employeeStatus;			
		}
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		if(grade==null) {
			this.grade = new Grade();
		}else {
			this.grade = grade;			
		}
	}

	public String getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(String supervisor) {
		if(supervisor==null) {
			this.supervisor = new String();
		}else {
			this.supervisor = supervisor;			
		}
	}

	public AssignmentReason getAssignmentReason() {
		return assignmentReason;
	}

	public void setAssignmentReason(AssignmentReason assignmentReason) {
		if(assignmentReason==null) {
			this.assignmentReason = new AssignmentReason();
		}else {
			this.assignmentReason = assignmentReason;			
		}
	}

	public PositionSlot getPositionSlot() {
		return positionSlot;
	}

	public void setPositionSlot(PositionSlot positionSlot) {
		if(positionSlot==null) {
			this.positionSlot =new PositionSlot();
		}else {
			this.positionSlot = positionSlot;			
		}
	}

	public Boolean getIsPrimary() {
		return isPrimary;
	}

	public void setIsPrimary(Boolean isPrimary) {
		if(isPrimary==null) {
			this.isPrimary = false;
		}else {
			this.isPrimary = isPrimary;			
		}
	}

	public String getFileAssignmentDoc() {
		return fileAssignmentDoc;
	}

	public void setFileAssignmentDoc(String fileAssignmentDoc) {
		if(fileAssignmentDoc==null) {
			this.fileAssignmentDoc = new String();
		}else {
			this.fileAssignmentDoc = fileAssignmentDoc;			
		}
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		if(note==null) {
			this.note = new String();
		}else {			
			this.note = note;
		}
	}

	public Date getFinalProcessDate() {
		return finalProcessDate;
	}

	public void setFinalProcessDate(Date finalProcessDate) {
		if(finalProcessDate==null) {
			this.finalProcessDate = new Date();
		}else {
			this.finalProcessDate = finalProcessDate;			
		}
	}

	public String getAssignmentDocNumber() {
		return assignmentDocNumber;
	}

	public void setAssignmentDocNumber(String assignmentDocNumber) {
		if(assignmentDocNumber==null) {
			this.assignmentDocNumber = new String();
		}else {
			this.assignmentDocNumber = assignmentDocNumber;			
		}
	}

	public Lov getLovActy() {
		return lovActy;
	}

	public void setLovActy(Lov lovActy) {
		if(lovActy==null) {
			this.lovActy = new Lov();
		}else {
			this.lovActy = lovActy;			
		}
	}
}
