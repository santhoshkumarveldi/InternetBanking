package com.altimetrick.bank.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.altimetrick.bank.dao.ICustomerDAO;
import com.altimetrick.bank.dto.CustomerDetailsDTO;
import com.altimetrick.bank.dto.LoginDetailDTO;
import com.altimetrick.bank.dto.LoginResposeDTO;
import com.altimetrick.bank.exceptions.ApplicationException;
import com.altimetrick.bank.exceptions.NotFoundException;
import com.altimetrick.bank.model.Customer;
import com.altimetrick.bank.security.JwtTokenProvider;
import com.altimetrick.bank.service.CustomerService;

/**
 * Customer service implementation class.
 * 
 * @author VSanthosh
 *
 */
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
	
	final Logger logg = LoggerFactory.getLogger(CustomerServiceImpl.class);

	@Autowired
	private ICustomerDAO customerRepository;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public LoginResposeDTO loadCustomerLoginDetails(LoginDetailDTO loginDetailParameter) throws ApplicationException {
		String loginName = loginDetailParameter.getLoginName();
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginName,
					String.valueOf(loginDetailParameter.getPassword())));

			Customer customer = customerRepository.getCustomerDetailsByLoginId(loginName);

			String token = jwtTokenProvider.createToken(loginName, customer.getPermissions());

			return new LoginResposeDTO(token, customer);
		} catch (BadCredentialsException e) {
			throw new NotFoundException("Given customer credentials not exist in the system", HttpStatus.NOT_FOUND);
		} catch (AuthenticationException e) {
			logg.info("Given customer credentials not exist in the system", e);
			throw new ApplicationException("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public CustomerDetailsDTO getCustomerDetailsByCustomerId(long customerId) throws ApplicationException {

		CustomerDetailsDTO customerDetailsDTO = null;
		try {
			Customer customer = customerRepository.loadCustomerDetailsByCustomerId(customerId);
			if (customer != null) {
				customerDetailsDTO = new CustomerDetailsDTO(customer);
			}
		} catch (AuthenticationException e) {
			logg.info("Given customer id not exist in the system", e);
			throw new ApplicationException("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return customerDetailsDTO;
	}
}
