package com.altimetrick.bank.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.altimetrick.bank.dto.CustomerDetailsDTO;
import com.altimetrick.bank.exceptions.ApplicationException;
import com.altimetrick.bank.service.CustomerService;

/**
 * Customer rest controller
 * 
 * @author VSanthosh
 *
 */
@RestController
@RequestMapping(path = "/customer/dashboard")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@GetMapping("/profile/{customerId}")
	public CustomerDetailsDTO getCustomerDetailsById(@PathVariable long customerId) throws ApplicationException {
		return customerService.getCustomerDetailsByCustomerId(customerId);
	}

}
