package com.tansha.library.bookshelf.admin.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="tbl_payment_master")
public class PaymentConfirm {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "paymentID")
	private int paymentID;

	@Column(name="userID", nullable=false)
	private int userId;
	
	
	@Column(name="subscriptionID", nullable=false)
	private int subscriptionId;
	
	@Column(name="modeofPayment", nullable=false)
	private String modeofPayment;
	
	@Column(name="amount", nullable=false)
	private int amount;
	
	@Column(name="paidON")
	@CreationTimestamp
	private Date paidON;
	
	/**
	 * @return the paymentID
	 */
	public int getPaymentID() {
		return paymentID;
	}

	/**
	 * @param paymentID the paymentID to set
	 */
	public void setPaymentID(int paymentID) {
		this.paymentID = paymentID;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the subscriptionId
	 */
	public int getSubscriptionId() {
		return subscriptionId;
	}

	/**
	 * @param subscriptionId the subscriptionId to set
	 */
	public void setSubscriptionId(int subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	/**
	 * @return the modeofPayment
	 */
	public String getModeofPayment() {
		return modeofPayment;
	}

	/**
	 * @param modeofPayment the modeofPayment to set
	 */
	public void setModeofPayment(String modeofPayment) {
		this.modeofPayment = modeofPayment;
	}

	/**
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}

	/**
	 * @return the paidON
	 */
	public Date getPaidON() {
		return paidON;
	}

	/**
	 * @param paidON the paidON to set
	 */
	public void setPaidON(Date paidON) {
		this.paidON = paidON;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Payment [paymentID=" + paymentID + ", userId=" + userId + ", subscriptionId=" + subscriptionId
				+ ", modeofPayment=" + modeofPayment + ", amount=" + amount + ", paidON=" + paidON + "]";
	}

	
}