package com.bttraining.web.dto;

public class ReportDTO {

	private String kind;
	private String count;
	private String merchantAccountId;
	private String cardType;
	private String amountSettled;

	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getMerchantAccountId() {
		return merchantAccountId;
	}
	public void setMerchantAccountId(String merchantAccountId) {
		this.merchantAccountId = merchantAccountId;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getAmountSettled() {
		return amountSettled;
	}
	public void setAmountSettled(String amountSettled) {
		this.amountSettled = amountSettled;
	}

}
