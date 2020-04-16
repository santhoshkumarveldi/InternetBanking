package com.altimetrick.bank.restcontroller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.altimetrick.bank.dto.LoginDetailDTO;
import com.altimetrick.bank.dto.LoginResposeDTO;
import com.altimetrick.bank.exceptions.ApplicationException;
import com.altimetrick.bank.exceptions.BadRequestException;
import com.altimetrick.bank.service.CustomerService;

/**
 * Login rest controller
 * 
 * @author VSanthosh
 *
 */
@RestController
@RequestMapping(path = "/customer")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LoginController {

	@Autowired
	private CustomerService customerService;

	/**
	 * Customer login.
	 * 
	 * @param loginDetailParameter
	 * @return JWT token
	 * @throws ApplicationException
	 */
	@PostMapping(path = "/login")
	public LoginResposeDTO signIn(@RequestBody(required = true) LoginDetailDTO loginDetailParameter)
			throws ApplicationException {
		
		validation(loginDetailParameter);

		return customerService.loadCustomerLoginDetails(loginDetailParameter);
	}

	private void validation(LoginDetailDTO loginDetailParameter) {
		if (StringUtils.isBlank(loginDetailParameter.getLoginName())) {
			throw new BadRequestException("Login name is mandatory.", HttpStatus.BAD_REQUEST);
		}

		if (loginDetailParameter.getPassword().length == 0) {
			throw new BadRequestException("Password is mandatory.", HttpStatus.BAD_REQUEST);
		}
	}
}
