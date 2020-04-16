package com.altimetrick.bank.dao;

import com.altimetrick.bank.exceptions.ApplicationException;
import com.altimetrick.bank.model.Customer;

/**
 * Customer DAO interface
 * 
 * @author VSanthosh
 *
 */
public interface ICustomerDAO
{
	public Customer getCustomerDetailsByLoginId(String loginName) throws ApplicationException;
	
	public Customer loadCustomerDetailsByCustomerId(long customerId) throws ApplicationException;
}
