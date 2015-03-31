package org.visminer.core.persistence.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.visminer.core.model.Commit;
import org.visminer.core.persistence.Connection;

public class CommitDAO {

	public List<Commit.Database> getByNames(List<String> names) {

		EntityManager em = Connection.getEntityManager();
		TypedQuery<Commit.Database> query = em.createQuery(
				"select c from Commit c where c.name in :arg0",
				Commit.Database.class);
		query.setParameter("arg0", names);

		try {
			List<Commit.Database> result = query.getResultList();
			em.close();
			return result;
		} catch (NoResultException e) {
			return null;
		}

	}

	public List<Commit.Database> getByTree(int treeId) {

		EntityManager em = Connection.getEntityManager();
		TypedQuery<Commit.Database> query = em
				.createQuery(
						"select c from Commit c join c.trees t where t.id = :arg0 order by c.date asc",
						Commit.Database.class);
		query.setParameter("arg0", treeId);

		try {
			List<Commit.Database> result = query.getResultList();
			em.close();
			return result;
		} catch (NoResultException e) {
			return null;
		}

	}

	public List<Commit.Database> getByRepository(int repositoryId) {

		EntityManager em = Connection.getEntityManager();
		TypedQuery<Commit.Database> query = em
				.createQuery(
						"select c from Commit c join c.trees t join t.repository r where r.id = :arg0",
						Commit.Database.class);
		query.setParameter("arg0", repositoryId);

		try {
			List<Commit.Database> resp = query.getResultList();
			em.close();
			return resp;
		} catch (NoResultException e) {
			return null;
		}

	}

}