package com.example.demo.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "payments")
public class Payment {
	@Id	private String id;
	private String merchantRequestID;
	private String checkoutRequestID;
	private int resultCode;
	private String resultDesc;
	private String amount;
	private String mpesaReceiptNumber;
	private Date transactionDate;
	private String phoneNumber;
	public String getMerchantRequestID() {
		return merchantRequestID;
	}
	public void setMerchantRequestID(String merchantRequestID) {
		this.merchantRequestID = merchantRequestID;
	}
	public String getCheckoutRequestID() {
		return checkoutRequestID;
	}
	public void setCheckoutRequestID(String checkoutRequestID) {
		this.checkoutRequestID = checkoutRequestID;
	}
	public int getResultCode() {
		return resultCode;
	}
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	public String getResultDesc() {
		return resultDesc;
	}
	public void setResultDesc(String resultDesc) {
		this.resultDesc = resultDesc;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getMpesaReceiptNumber() {
		return mpesaReceiptNumber;
	}
	public void setMpesaReceiptNumber(String mpesaReceiptNumber) {
		this.mpesaReceiptNumber = mpesaReceiptNumber;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
}
