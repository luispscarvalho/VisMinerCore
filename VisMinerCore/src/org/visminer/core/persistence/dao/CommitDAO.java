package org.visminer.core.persistence.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.visminer.core.model.database.CommitDB;
import org.visminer.core.persistence.Connection;

public class CommitDAO {

	public List<CommitDB> getByNames(List<String> names) {

		EntityManager em = Connection.getEntityManager();
		TypedQuery<CommitDB> query = em.createQuery(
				"select c from Commit c where c.name in :arg0",
				CommitDB.class);
		query.setParameter("arg0", names);

		try {
			List<CommitDB> result = query.getResultList();
			em.close();
			return result;
		} catch (NoResultException e) {
			return null;
		}

	}

	public List<CommitDB> getByTree(int treeId) {

		EntityManager em = Connection.getEntityManager();
		TypedQuery<CommitDB> query = em
				.createQuery(
						"select c from Commit c join c.trees t where t.id = :arg0 order by c.date asc",
						CommitDB.class);
		query.setParameter("arg0", treeId);

		try {
			List<CommitDB> result = query.getResultList();
			em.close();
			return result;
		} catch (NoResultException e) {
			return null;
		}

	}

	public List<CommitDB> getByRepository(int repositoryId) {

		EntityManager em = Connection.getEntityManager();
		TypedQuery<CommitDB> query = em
				.createQuery(
						"select c from Commit c join c.trees t join t.repository r where r.id = :arg0",
						CommitDB.class);
		query.setParameter("arg0", repositoryId);

		try {
			List<CommitDB> resp = query.getResultList();
			em.close();
			return resp;
		} catch (NoResultException e) {
			return null;
		}

	}

}