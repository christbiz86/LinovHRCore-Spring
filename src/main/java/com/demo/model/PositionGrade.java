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
@Table(name = "core_position_grades", uniqueConstraints = @UniqueConstraint(columnNames = {"grade_id", "position_id"}))
public class PositionGrade extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@JoinColumn(name = "position_id", referencedColumnName = "id")
	@OneToOne
	private Position position;

	@JoinColumn(name = "grade_id", referencedColumnName = "id")
	@OneToOne
	private Grade grade;
	
	@Column(name = "bottom_rate")
	private Long bottom;
	
	@Column(name = "mid_rate")
	private Long mid;
	
	@Column(name = "top_rate")
	private Long top;

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		if (position == null) {
			this.position = new Position();
		} else {
			this.position = position;	
		}
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		if (grade == null) {
			this.grade = new Grade();
		} else {
			this.grade = grade;
		}
	}

	public Long getBottom() {
		return bottom;
	}

	public void setBottom(Long bottom) {
		if (bottom == null) {
			this.bottom = new Long(0);
		} else {
			this.bottom = bottom;	
		}
	}

	public Long getMid() {
		return mid;
	}

	public void setMid(Long mid) {
		if (mid == null) {
			this.mid = new Long (0);
		} else {
			this.mid = mid;
		}
	}

	public Long getTop() {
		return top;
	}

	public void setTop(Long top) {
		if (top == null) {
			this.top = new Long (0);
		} else {
			this.top = top;
		}
	}
}
