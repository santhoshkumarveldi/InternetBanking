package com.altimetrick.bank.service;

import com.altimetrick.bank.dto.CustomerDetailsDTO;
import com.altimetrick.bank.dto.LoginDetailDTO;
import com.altimetrick.bank.dto.LoginResposeDTO;
import com.altimetrick.bank.exceptions.ApplicationException;

/**
 * Customer service interface.
 * 
 * @author VSanthosh
 *
 */
public interface CustomerService {

	/**
	 * load the customer login details
	 * 
	 * @param loginDetailParameter
	 * @return JWT token
	 * @throws ApplicationException
	 */
	public LoginResposeDTO loadCustomerLoginDetails(LoginDetailDTO loginDetailParameter) throws ApplicationException;

	/**
	 * Load the customer details based on customer id
	 * @param customerId
	 * @return CustomerDetailsDTO object
	 * @throws ApplicationException
	 */
	public CustomerDetailsDTO getCustomerDetailsByCustomerId(long customerId) throws ApplicationException;

}
