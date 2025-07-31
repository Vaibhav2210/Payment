package com.vaibhav.ws.peer.exception;

public class UserNullPointerException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	private String firstname;
	private String lastname;
	private String phone;
	private String email;
	private String amount;
	private String currency;
	private String peerBankCode;
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getPeerBankCode() {
		return peerBankCode;
	}
	public void setPeerBankCode(String peerBankCode) {
		this.peerBankCode = peerBankCode;
	}
	@Override
	public String toString() {
		return "NullPointerException [firstname=" + firstname + ", lastname=" + lastname + ", phone=" + phone
				+ ", email=" + email + ", amount=" + amount + ", currency=" + currency + ", peerBankCode="
				+ peerBankCode + "]";
	}
	
	
	public UserNullPointerException(String msg) {
		super(msg);
	}
	
}
