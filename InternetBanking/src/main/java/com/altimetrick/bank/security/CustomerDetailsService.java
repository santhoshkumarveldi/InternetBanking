package com.altimetrick.bank.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.altimetrick.bank.dao.ICustomerDAO;
import com.altimetrick.bank.exceptions.ApplicationException;
import com.altimetrick.bank.model.Customer;

/**
 * CustomerDetails service class.
 * 
 * @author VSanthosh
 *
 */
@Service
public class CustomerDetailsService implements UserDetailsService
{

	@Autowired
	private ICustomerDAO userRepository;

	@Override
	public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException
	{
		Customer customer;
		try {
			customer = userRepository.getCustomerDetailsByLoginId(loginId);
		} catch (Exception e) {
			throw new ApplicationException("Internal server issue", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (customer == null) {
			throw new UsernameNotFoundException("Customer login name '" + loginId + "' not found");
		}

		return org.springframework.security.core.userdetails.User//
				.withUsername(loginId)//
				.password(customer.getPassword())//
				.authorities(customer.getPermissions())//
				.accountExpired(false)//
				.accountLocked(false)//
				.credentialsExpired(false)//
				.disabled(false)//
				.build();
	}

}
