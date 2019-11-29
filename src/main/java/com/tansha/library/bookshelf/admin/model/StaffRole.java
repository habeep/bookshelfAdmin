package com.tansha.library.bookshelf.admin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name="tbl_staff_roles")
public class StaffRole {
	
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name="staff_id", nullable=false)
	private int staff_id;
	
	@Column(name="role_id", nullable=false)
	private int role_id;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the staff_id
	 */
	public int getStaff_id() {
		return staff_id;
	}

	/**
	 * @param staff_id the staff_id to set
	 */
	public void setStaff_id(int staff_id) {
		this.staff_id = staff_id;
	}

	/**
	 * @return the role_id
	 */
	public int getRole_id() {
		return role_id;
	}

	/**
	 * @param role_id the role_id to set
	 */
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StaffRole [id=" + id + ", staff_id=" + staff_id + ", role_id=" + role_id + "]";
	}
	


		

}
