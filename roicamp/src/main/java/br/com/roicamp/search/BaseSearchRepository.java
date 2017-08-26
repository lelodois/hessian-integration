package br.com.roicamp.search;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseSearchRepository {

	@Autowired
	private SessionFactory sessionFactory;

	public BaseSearchRepository() {
	}

	protected Query getQuery(String query) {
		return getSession().createQuery(query);
	}

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
}