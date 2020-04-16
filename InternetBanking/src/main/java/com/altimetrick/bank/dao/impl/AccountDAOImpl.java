package com.altimetrick.bank.dao.impl;

import org.springframework.stereotype.Repository;

import com.altimetrick.bank.dao.AbstractDao;
import com.altimetrick.bank.dao.IAccountDAO;
import com.altimetrick.bank.model.AccountDetails;
/**
 * Account DAO implementation class
 * 
 * @author VSanthosh
 *
 */
@Repository
public class AccountDAOImpl extends AbstractDao<Long, AccountDetails> implements IAccountDAO {


}
