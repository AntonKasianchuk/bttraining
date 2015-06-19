package com.bttraining.web.dto;

import java.util.Date;

public class TransactionInfoDTO {

	private String id;
	private Date date;
	private String type;
	private String status;
	private String customerName;
	private String paymentInformation;
	private String amount;
	private Boolean isSettled;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getPaymentInformation() {
		return paymentInformation;
	}

	public void setPaymentInformation(String paymentInformation) {
		this.paymentInformation = paymentInformation;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Boolean getIsSettled() {
		return isSettled;
	}

	public void setIsSettled(Boolean isSettled) {
		this.isSettled = isSettled;
	}
}
