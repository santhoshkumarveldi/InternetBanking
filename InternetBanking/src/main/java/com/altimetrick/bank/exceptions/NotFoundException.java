package com.altimetrick.bank.exceptions;

import org.springframework.http.HttpStatus;

/**
 * NotFoundException class.
 * 
 * @author VSanthosh
 *
 */
public class NotFoundException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	private final String message;
	private final HttpStatus httpStatus;

	public NotFoundException(String message, HttpStatus httpStatus)
	{
		this.message = message;
		this.httpStatus = httpStatus;
	}

	@Override
	public String getMessage()
	{
		return message;
	}

	public HttpStatus getHttpStatus()
	{
		return httpStatus;
	}

}
