package org.visminer.core.persistence.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.visminer.core.model.database.CommitterDB;
import org.visminer.core.persistence.Connection;

public class CommitterDAO {

	public CommitterDB getByNameAndEmail(String name, String email) {

		EntityManager em = Connection.getEntityManager();
		TypedQuery<CommitterDB> query = em
				.createQuery(
						"select c from Committer c where c.email = :arg0 and c.name = :arg1",
						CommitterDB.class);
		query.setParameter("arg0", email);
		query.setParameter("arg1", name);

		try {
			CommitterDB result = query.getSingleResult();
			em.close();
			return result;
		} catch (NoResultException e) {
			return null;
		}

	}

	public List<CommitterDB> saveAll(List<CommitterDB> committers) {

		EntityManager em = Connection.getEntityManager();
		em.getTransaction().begin();
		List<CommitterDB> committers2 = new ArrayList<CommitterDB>();

		for (CommitterDB c : committers) {
			committers2.add(em.merge(c));
		}

		em.getTransaction().commit();
		em.close();

		return committers2;

	}

	public List<CommitterDB> getAll() {

		EntityManager em = Connection.getEntityManager();
		TypedQuery<CommitterDB> query = em.createQuery(
				"select c from Committer c", CommitterDB.class);

		try {
			List<CommitterDB> result = query.getResultList();
			em.close();
			return result;
		} catch (NoResultException e) {
			return null;
		}

	}

	public List<CommitterDB> getByRepository(int repositoryId) {

		EntityManager em = Connection.getEntityManager();
		TypedQuery<CommitterDB> query = em
				.createQuery(
						"select c from Committer c join c.repositories r where r.id = :arg0",
						CommitterDB.class);
		query.setParameter("arg0", repositoryId);

		try {
			List<CommitterDB> result = query.getResultList();
			em.close();
			return result;
		} catch (NoResultException e) {
			return null;
		}

	}

}