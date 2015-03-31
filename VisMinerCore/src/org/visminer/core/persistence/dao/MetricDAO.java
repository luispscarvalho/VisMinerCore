package org.visminer.core.persistence.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.visminer.core.model.Metric;
import org.visminer.core.persistence.Connection;

public class MetricDAO {

	public Metric.Database save(Metric.Database metric) {

		EntityManager em = Connection.getEntityManager();
		em.getTransaction().begin();
		Metric.Database response = em.merge(metric);
		em.getTransaction().commit();
		em.close();
		return response;

	}

	public List<Metric.Database> getAll() {

		EntityManager em = Connection.getEntityManager();
		TypedQuery<Metric.Database> query = em.createQuery(
				"select m from Metric m", Metric.Database.class);

		try {
			List<Metric.Database> result = query.getResultList();
			em.close();
			return result;
		} catch (NoResultException e) {
			return null;
		}

	}

	public Metric.Database getByName(String name) {

		EntityManager em = Connection.getEntityManager();
		TypedQuery<Metric.Database> query = em.createQuery(
				"select m from Metric m where m.name = :arg0",
				Metric.Database.class);
		query.setParameter("arg0", name);

		try {
			Metric.Database result = query.getSingleResult();
			em.close();
			return result;
		} catch (NoResultException e) {
			return null;
		}

	}

	public Metric.Database get(int id) {

		EntityManager em = Connection.getEntityManager();
		Metric.Database metric = em.find(Metric.Database.class, id);
		em.close();
		return metric;

	}

}