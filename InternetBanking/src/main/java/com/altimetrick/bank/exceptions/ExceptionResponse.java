package com.altimetrick.bank.exceptions;

import java.util.Date;

/**
 * ExceptionResponse Class.
 * 
 * @author VSanthosh
 *
 */
public class ExceptionResponse
{
	private Date timeStamp;
	private int httpCode;
	private String httpCodeMessage;
	private String errorMessage;

	/**
	 * Default Constructor
	 */
	public ExceptionResponse()
	{

	}

	/**
	 * @param timestamp
	 * @param httpCode
	 * @param httpCodeMessage
	 * @param errorMessage
	 */
	public ExceptionResponse(Date timeStamp, int httpCode, String httpCodeMessage, String errorMessage)
	{
		this.timeStamp = timeStamp;
		this.httpCode = httpCode;
		this.httpCodeMessage = httpCodeMessage;
		this.errorMessage = errorMessage;
	}

	/**
	 * @return the timeStamp
	 */
	public Date getTimeStamp()
	{
		return timeStamp;
	}

	/**
	 * @param timestamp the timeStamp to set
	 */
	public void setTimeStamp(Date timeStamp)
	{
		this.timeStamp = timeStamp;
	}

	/**
	 * @return the httpCode
	 */
	public int getHttpCode()
	{
		return httpCode;
	}

	/**
	 * @param httpCode the httpCode to set
	 */
	public void setHttpCode(int httpCode)
	{
		this.httpCode = httpCode;
	}

	/**
	 * @return the httpCodeMessage
	 */
	public String getHttpCodeMessage()
	{
		return httpCodeMessage;
	}

	/**
	 * @param httpCodeMessage the httpCodeMessage to set
	 */
	public void setHttpCodeMessage(String httpCodeMessage)
	{
		this.httpCodeMessage = httpCodeMessage;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage()
	{
		return errorMessage;
	}

	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage)
	{
		this.errorMessage = errorMessage;
	}

}
