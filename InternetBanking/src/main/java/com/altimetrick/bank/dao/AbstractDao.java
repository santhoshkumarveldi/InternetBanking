package com.altimetrick.bank.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Common abstract dao
 * @author VSanthosh
 *
 * @param <PK>
 * @param <T>
 */
public abstract class AbstractDao<PK extends Serializable, T>
{

	private final Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public AbstractDao()
	{
		this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass

		()).getActualTypeArguments()[1];
	}

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession()
	{
		// java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
		return sessionFactory.getCurrentSession();
	}

	public T getByKey(PK key)
	{
		return getSession().get(persistentClass, key);
	}

	public void persist(T entity)
	{
		getSession().persist(entity);
	}

	@SuppressWarnings("hiding")
	public <T> void insertAll(List<T> obj)
	{
		for (int i = 0; i < obj.size(); i++) {
			getSession().persist(obj.get(i));
			if (i % 500 == 0) {
				getSession().flush();
				getSession().clear();
			}
		}
	}

	public void delete(T entity)
	{
		getSession().delete(entity);
	}

	protected List<T> findAllResults()
	{
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<T> criteria = builder.createQuery(persistentClass);
		Root<T> variableRoot = criteria.from(persistentClass);
		criteria.select(variableRoot);
		return getSession().createQuery(criteria).getResultList();
	}

	protected CriteriaBuilder getCriteriaBuilder()
	{
		return getSession().getCriteriaBuilder();
	}

	protected List<T> getResultList(CriteriaQuery<T> criteria)
	{
		return getSession().createQuery(criteria).getResultList();
	}

	protected T getSingleResult(CriteriaQuery<T> criteria)
	{
		return getSession().createQuery(criteria).getSingleResult();
	}

	public void saveOrUpdate(T entity)
	{
		getSession().saveOrUpdate(entity);
	}

	public void merge(T entity)
	{
		getSession().merge(entity);
	}

	public NativeQuery<?> createsqlQuery(String s)
	{
		return getSession().createSQLQuery(s);
	}

	public Long saveandreturnpk(T entity)
	{
		return (Long) getSession().save(entity);
	}

}