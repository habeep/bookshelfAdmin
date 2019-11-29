package com.tansha.library.bookshelf.admin.model;

import java.sql.Date;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name="tbl_staff_master")
public class Staff {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name="roleId", nullable=false)
	private int roleId;
	
	
	@Column(name="password", nullable=false, length=25)
	private String password;
	
	
	
	
	@Column(name="staffName", nullable=false, length=45)
	private String staffName;
	
	@Column(name="staffLastName")
	private String staffLastName;
	
	@Column(name="gender", nullable=false)
	private String gender;
	
	@Column(name="emailId", nullable=false)
	private String emailId;
	
	@Column(name="address", nullable=false)
	private String address;
	
	@Column(name="phoneNumber")
	private long phoneNumber;
	
	@Column(name="mobileNumber", nullable=false)
	private long mobileNumber;
	
	@Column(name="town", nullable=false)
	private String town;
	
	@Column(name="city", nullable=false)
	private String city;
	
	@Column(name="state", nullable=false)
	private String state;
	
    @DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name="dateofbirth", nullable=false)
	private String dateofbirth;
	
	@Column(name="pincode", nullable=false)
	private int pincode;
	
	@Pattern(regexp = "[A-Za-z0-9]*")
	@Column(name="idCardNumber", nullable=false)
	private String idCardNumber;
		
	@Column(name="idCard", nullable=false)
	private String idCard;
	
	@Column(name="isDeliveryMan", nullable=false)
	private int isDeliveryMan;
	
	@Column(name="createdAt")
	@CreationTimestamp()
	private Date createdAt;
	
	@Column(name="modifiedAt")
	@UpdateTimestamp
	private Date modifiedAt;
	
	@ColumnDefault(value="1")
	@Column(name="isActive")
	private int isActive;
	
	
	/**
	 * @return the isActive
	 */
	public int getIsActive() {
		return isActive;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}


	
	public Staff() {
		
	}
	
	

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the roleId
	 */
	public int getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	

	/**
	 * @return the staffName
	 */
	public String getStaffName() {
		return staffName;
	}

	/**
	 * @param staffName the staffName to set
	 */
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	/**
	 * @return the staffLastName
	 */
	public String getStaffLastName() {
		return staffLastName;
	}

	/**
	 * @param staffLastName the staffLastName to set
	 */
	public void setStaffLastName(String staffLastName) {
		this.staffLastName = staffLastName;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the phoneNumber
	 */
	public long getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the mobileNumber
	 */
	public long getMobileNumber() {
		return mobileNumber;
	}

	/**
	 * @param mobileNumber the mobileNumber to set
	 */
	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	/**
	 * @return the town
	 */
	public String getTown() {
		return town;
	}

	/**
	 * @param town the town to set
	 */
	public void setTown(String town) {
		this.town = town;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the dateofbirth
	 */
	public String getDateofbirth() {
		return dateofbirth;
	}

	/**
	 * @param dateofbirth the dateofbirth to set
	 */
	public void setDateofbirth(String dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	/**
	 * @return the pincode
	 */
	public int getPincode() {
		return pincode;
	}

	/**
	 * @param pincode the pincode to set
	 */
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	/**
	 * @return the idCardNumber
	 */
	public String getIdCardNumber() {
		return idCardNumber;
	}

	/**
	 * @param idCardNumber the idCardNumber to set
	 */
	public void setIdCardNumber(String idCardNumber) {
		this.idCardNumber = idCardNumber;
	}

	/**
	 * @return the idCard
	 */
	public String getIdCard() {
		return idCard;
	}

	/**
	 * @param idCard the idCard to set
	 */
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	/**
	 * @return the isDeliveryMan
	 */
	public int getIsDeliveryMan() {
		return isDeliveryMan;
	}

	/**
	 * @param isDeliveryMan the isDeliveryMan to set
	 */
	public void setIsDeliveryMan(int isDeliveryMan) {
		this.isDeliveryMan = isDeliveryMan;
	}

	/**
	 * @return the createdAt
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * @return the modifiedAt
	 */
	public Date getModifiedAt() {
		return modifiedAt;
	}

	/**
	 * @param modifiedAt the modifiedAt to set
	 */
	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	
	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Staff [id=" + id + ", roleId=" + roleId + ", password=" + password + ", staffName=" + staffName
				+ ", staffLastName=" + staffLastName + ", gender=" + gender + ", emailId=" + emailId + ", address="
				+ address + ", phoneNumber=" + phoneNumber + ", mobileNumber=" + mobileNumber + ", town=" + town
				+ ", city=" + city + ", state=" + state + ", dateofbirth=" + dateofbirth + ", pincode=" + pincode
				+ ", idCardNumber=" + idCardNumber + ", idCard=" + idCard + ", isDeliveryMan=" + isDeliveryMan
				+ ", createdAt=" + createdAt + ", modifiedAt=" + modifiedAt + ", isActive=" + isActive + "]";
	}

	

	

}
