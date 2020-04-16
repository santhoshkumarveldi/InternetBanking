package com.altimetrick.bank.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Optional;

import com.altimetrick.bank.model.Customer;

/**
 * Customer details data transfer object.
 * 
 * @author VSanthosh
 *
 */
public class CustomerDetailsDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String customerName;

	private String nickName;

	private Long accountId;

	private int accountNumber;

	private String ifscCode;

	private BigDecimal accountBalance;

	private String emailId;

	private String phoneNumber;

	private String city;

	private String state;

	private String country;

	/**
	 * 
	 */
	public CustomerDetailsDTO() {
		super();
	}

	/**
	 * set the customer details
	 * @param customer
	 * @param accountDetails
	 */
	public CustomerDetailsDTO(Customer customer) {
		Optional.ofNullable(customer);
		this.customerName = customer.getCustomerName();
		this.nickName = customer.getNickName();
		this.emailId = customer.getEmailId();
		this.phoneNumber = customer.getPhoneNumber();
		this.city = customer.getCity();
		this.state = customer.getState();
		this.country = customer.getCountry();
		this.accountNumber = customer.getAccountDetails().getAccountNumber();
		this.ifscCode = customer.getAccountDetails().getIfscCode();
		this.accountBalance = customer.getAccountDetails().getAccountBalance();
	}

	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * @return the nickName
	 */
	public String getNickName() {
		return nickName;
	}

	/**
	 * @param nickName the nickName to set
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	/**
	 * @return the accountId
	 */
	public Long getAccountId() {
		return accountId;
	}

	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	/**
	 * @return the accountNumber
	 */
	public int getAccountNumber() {
		return accountNumber;
	}

	/**
	 * @param accountNumber the accountNumber to set
	 */
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	/**
	 * @return the ifscCode
	 */
	public String getIfscCode() {
		return ifscCode;
	}

	/**
	 * @param ifscCode the ifscCode to set
	 */
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	/**
	 * @return the accountBalance
	 */
	public BigDecimal getAccountBalance() {
		return accountBalance;
	}

	/**
	 * @param accountBalance the accountBalance to set
	 */
	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
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
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

}
