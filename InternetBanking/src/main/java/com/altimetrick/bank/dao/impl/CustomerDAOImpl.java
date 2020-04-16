package com.altimetrick.bank.dao.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.altimetrick.bank.dao.AbstractDao;
import com.altimetrick.bank.dao.ICustomerDAO;
import com.altimetrick.bank.exceptions.ApplicationException;
import com.altimetrick.bank.model.Customer;
/**
 * Customer DAO implementation class
 * 
 * @author VSanthosh
 *
 */
@Repository
public class CustomerDAOImpl extends AbstractDao<Long, Customer> implements ICustomerDAO {

	@Override
	public Customer getCustomerDetailsByLoginId(String loginName) throws ApplicationException {
		CriteriaBuilder builder = getCriteriaBuilder();
		CriteriaQuery<Customer> criteria = builder.createQuery(Customer.class);
		Root<Customer> root = criteria.from(Customer.class);
		criteria.select(root).where(builder.equal(root.get("loginName"), loginName));
		return getSingleResult(criteria);
	}

	@Override
	public Customer loadCustomerDetailsByCustomerId(long customerId) throws ApplicationException {
		CriteriaBuilder builder = getCriteriaBuilder();
		CriteriaQuery<Customer> criteria = builder.createQuery(Customer.class);
		Root<Customer> root = criteria.from(Customer.class);
		criteria.select(root).where(builder.equal(root.get("customerId"), customerId));
		return getSingleResult(criteria);
	}

}
