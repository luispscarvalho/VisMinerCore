package org.visminer.core.persistence.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.visminer.core.model.database.RepositoryDB;
import org.visminer.core.persistence.Connection;

public class RepositoryDAO {

	public RepositoryDB save(RepositoryDB repository) {

		EntityManager em = Connection.getEntityManager();
		em.getTransaction().begin();
		repository = em.merge(repository);
		em.getTransaction().commit();
		em.close();
		return repository;

	}

	public RepositoryDB getByPath(String sha) {

		EntityManager em = Connection.getEntityManager();
		TypedQuery<RepositoryDB> query = em.createQuery(
				"select r from Repository r where r.sha = :arg0",
				RepositoryDB.class);
		query.setParameter("arg0", sha);
		try {
			RepositoryDB r = query.getSingleResult();
			em.close();
			return r;
		} catch (NoResultException e) {
			em.close();
			return null;
		}

	}

	public List<RepositoryDB> getByCommitter(int committerId) {

		EntityManager em = Connection.getEntityManager();
		TypedQuery<RepositoryDB> query = em
				.createQuery(
						"select r from Repository r join r.committers c where c.id = :arg0",
						RepositoryDB.class);
		query.setParameter("arg0", committerId);

		try {
			List<RepositoryDB> r = query.getResultList();
			em.close();
			return r;
		} catch (NoResultException e) {
			em.close();
			return null;
		}

	}

	public List<RepositoryDB> getAll() {

		EntityManager em = Connection.getEntityManager();
		TypedQuery<RepositoryDB> query = em.createQuery(
				"select r from Repository r", RepositoryDB.class);

		try {
			List<RepositoryDB> r = query.getResultList();
			em.close();
			return r;
		} catch (NoResultException e) {
			em.close();
			return null;
		}

	}

}
