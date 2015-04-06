package org.visminer.core.persistence.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.visminer.core.model.database.MetricDB;
import org.visminer.core.persistence.Connection;

public class MetricDAO {

	public MetricDB save(MetricDB metric) {

		EntityManager em = Connection.getEntityManager();
		em.getTransaction().begin();
		MetricDB response = em.merge(metric);
		em.getTransaction().commit();
		em.close();
		return response;

	}

	public List<MetricDB> getAll() {

		EntityManager em = Connection.getEntityManager();
		TypedQuery<MetricDB> query = em.createQuery(
				"select m from Metric m", MetricDB.class);

		try {
			List<MetricDB> result = query.getResultList();
			em.close();
			return result;
		} catch (NoResultException e) {
			return null;
		}

	}

	public MetricDB getByName(String name) {

		EntityManager em = Connection.getEntityManager();
		TypedQuery<MetricDB> query = em.createQuery(
				"select m from Metric m where m.name = :arg0",
				MetricDB.class);
		query.setParameter("arg0", name);

		try {
			MetricDB result = query.getSingleResult();
			em.close();
			return result;
		} catch (NoResultException e) {
			return null;
		}

	}

	public MetricDB get(int id) {

		EntityManager em = Connection.getEntityManager();
		MetricDB metric = em.find(MetricDB.class, id);
		em.close();
		return metric;

	}

}