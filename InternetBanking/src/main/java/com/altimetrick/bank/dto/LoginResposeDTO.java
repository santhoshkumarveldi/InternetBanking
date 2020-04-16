package com.altimetrick.bank.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.altimetrick.bank.model.Customer;

/**
 * Login details data transfer object
 * 
 * @author VSanthosh
 *
 */
public class LoginResposeDTO implements Serializable
{

	private static final long serialVersionUID = 1L;

	private String jwtToken;

	private String role;

	private List<String> permissions;

	/**
	 * default constructor
	 */
	public LoginResposeDTO()
	{
	}

	/**
	 * @param jwtToken
	 * @param user
	 */
	public LoginResposeDTO(String jwtToken, Customer customer)
	{
		Objects.requireNonNull(customer);
		this.jwtToken = jwtToken;
		this.role = customer.getRoleName();
		this.permissions = customer.getPermissions().stream().map(p -> p.getPermissionName())//
				.collect(Collectors.<String>toList());
	}

	/**
	 * @return the jwtToken
	 */
	public String getJwtToken()
	{
		return jwtToken;
	}

	/**
	 * @param jwtToken the jwtToken to set
	 */
	public void setJwtToken(String jwtToken)
	{
		this.jwtToken = jwtToken;
	}

	/**
	 * @return the role
	 */
	public String getRole()
	{
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(String role)
	{
		this.role = role;
	}

	/**
	 * @return the permissions
	 */
	public List<String> getPermissions()
	{
		return permissions;
	}

	/**
	 * @param permissions the permissions to set
	 */
	public void setPermissions(List<String> permissions)
	{
		this.permissions = permissions;
	}

}
