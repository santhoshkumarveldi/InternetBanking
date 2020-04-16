package com.altimetrick.bank.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The Permissions class.
 * 
 * @author VSanthosh
 *
 */
@Entity
@Table(name = "permissions")
public class Permissions implements GrantedAuthority
{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "permissions_id")
	private String id;

	private String permissionName;

	/**
	 * default constructor
	 */
	public Permissions()
	{
		super();
	}

	/**
	 * @param permissionID
	 */
	public Permissions(String permissionName)
	{
		this.permissionName = permissionName;
	}

	/**
	 * @return the id
	 */
	@JsonIgnore
	public String getId()
	{
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id)
	{
		this.id = id;
	}

	/**
	 * @return the permissionName
	 */
	public String getPermissionName() {
		return permissionName;
	}

	/**
	 * @param permissionName the permissionName to set
	 */
	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	@Override
	@JsonIgnore
	public String getAuthority()
	{
		return this.permissionName;
	}
}
