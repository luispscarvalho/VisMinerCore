package org.visminer.core.persistence.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.visminer.core.model.Committer;
import org.visminer.core.persistence.Connection;

public class CommitterDAO {

	public Committer.Database getByNameAndEmail(String name, String email) {

		EntityManager em = Connection.getEntityManager();
		TypedQuery<Committer.Database> query = em
				.createQuery(
						"select c from Committer c where c.email = :arg0 and c.name = :arg1",
						Committer.Database.class);
		query.setParameter("arg0", email);
		query.setParameter("arg1", name);

		try {
			Committer.Database result = query.getSingleResult();
			em.close();
			return result;
		} catch (NoResultException e) {
			return null;
		}

	}

	public List<Committer.Database> saveAll(List<Committer.Database> committers) {

		EntityManager em = Connection.getEntityManager();
		em.getTransaction().begin();
		List<Committer.Database> committers2 = new ArrayList<Committer.Database>();

		for (Committer.Database c : committers) {
			committers2.add(em.merge(c));
		}

		em.getTransaction().commit();
		em.close();

		return committers2;

	}

	public List<Committer.Database> getAll() {

		EntityManager em = Connection.getEntityManager();
		TypedQuery<Committer.Database> query = em.createQuery(
				"select c from Committer c", Committer.Database.class);

		try {
			List<Committer.Database> result = query.getResultList();
			em.close();
			return result;
		} catch (NoResultException e) {
			return null;
		}

	}

	public List<Committer.Database> getByRepository(int repositoryId) {

		EntityManager em = Connection.getEntityManager();
		TypedQuery<Committer.Database> query = em
				.createQuery(
						"select c from Committer c join c.repositories r where r.id = :arg0",
						Committer.Database.class);
		query.setParameter("arg0", repositoryId);

		try {
			List<Committer.Database> result = query.getResultList();
			em.close();
			return result;
		} catch (NoResultException e) {
			return null;
		}

	}

}