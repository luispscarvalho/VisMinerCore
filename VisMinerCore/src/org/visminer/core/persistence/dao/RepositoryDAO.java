package org.visminer.core.persistence.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.visminer.core.model.Repository;
import org.visminer.core.persistence.Connection;

public class RepositoryDAO {

	public Repository.Database save(Repository.Database repository) {

		EntityManager em = Connection.getEntityManager();
		em.getTransaction().begin();
		repository = em.merge(repository);
		em.getTransaction().commit();
		em.close();
		return repository;

	}

	public Repository.Database getByPath(String sha) {

		EntityManager em = Connection.getEntityManager();
		TypedQuery<Repository.Database> query = em.createQuery(
				"select r from Repository r where r.sha = :arg0",
				Repository.Database.class);
		query.setParameter("arg0", sha);
		try {
			Repository.Database r = query.getSingleResult();
			em.close();
			return r;
		} catch (NoResultException e) {
			em.close();
			return null;
		}

	}

	public List<Repository.Database> getByCommitter(int committerId) {

		EntityManager em = Connection.getEntityManager();
		TypedQuery<Repository.Database> query = em
				.createQuery(
						"select r from Repository r join r.committers c where c.id = :arg0",
						Repository.Database.class);
		query.setParameter("arg0", committerId);

		try {
			List<Repository.Database> r = query.getResultList();
			em.close();
			return r;
		} catch (NoResultException e) {
			em.close();
			return null;
		}

	}

	public List<Repository.Database> getAll() {

		EntityManager em = Connection.getEntityManager();
		TypedQuery<Repository.Database> query = em.createQuery(
				"select r from Repository r", Repository.Database.class);

		try {
			List<Repository.Database> r = query.getResultList();
			em.close();
			return r;
		} catch (NoResultException e) {
			em.close();
			return null;
		}

	}

}
