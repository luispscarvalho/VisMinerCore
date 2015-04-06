package org.visminer.core.persistence.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.visminer.core.model.database.TreeDB;
import org.visminer.core.persistence.Connection;

public class TreeDAO {

	public void saveAll(List<TreeDB> trees) {

		EntityManager em = Connection.getEntityManager();
		em.getTransaction().begin();

		for (TreeDB tree : trees)
			em.merge(tree);

		em.getTransaction().commit();
		em.close();

	}

	public List<TreeDB> getByRepository(int repositoryId) {

		EntityManager em = Connection.getEntityManager();
		TypedQuery<TreeDB> query = em.createQuery(
				"select t from Tree t where t.repository.id = :arg0",
				TreeDB.class);
		query.setParameter("arg0", repositoryId);

		try {
			List<TreeDB> result = query.getResultList();
			em.close();
			return result;
		} catch (NoResultException e) {
			return null;
		}

	}

}
