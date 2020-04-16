package com.altimetrick.bank.exceptions;

import java.util.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * ExceptionController advice class.
 * 
 * @author VSanthosh
 *
 */
@RestControllerAdvice
public class ExceptionController
{

	@ExceptionHandler(NotFoundException.class)
	public final ResponseEntity<ExceptionResponse> notFoundexception(NotFoundException ex) throws Exception
	{
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getHttpStatus().value(),
				ex.getHttpStatus().getReasonPhrase(), ex.getMessage());
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, ex.getHttpStatus());
	}

	@ExceptionHandler(BadRequestException.class)
	public final ResponseEntity<ExceptionResponse> badRequestException(BadRequestException ex) throws Exception
	{
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getHttpStatus().value(),
				ex.getHttpStatus().getReasonPhrase(), ex.getMessage());
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, ex.getHttpStatus());

	}

	@ExceptionHandler(ApplicationException.class)
	public final ResponseEntity<ExceptionResponse> applicationException(ApplicationException ex) throws Exception
	{
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getHttpStatus().value(),
				ex.getHttpStatus().getReasonPhrase(), ex.getMessage());
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, ex.getHttpStatus());
	}

}
