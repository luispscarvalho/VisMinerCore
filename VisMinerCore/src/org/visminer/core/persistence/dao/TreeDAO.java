package org.visminer.core.persistence.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.visminer.core.model.Tree;
import org.visminer.core.persistence.Connection;

public class TreeDAO {

	public void saveAll(List<Tree.Database> trees) {

		EntityManager em = Connection.getEntityManager();
		em.getTransaction().begin();

		for (Tree.Database tree : trees)
			em.merge(tree);

		em.getTransaction().commit();
		em.close();

	}

	public List<Tree.Database> getByRepository(int repositoryId) {

		EntityManager em = Connection.getEntityManager();
		TypedQuery<Tree.Database> query = em.createQuery(
				"select t from Tree t where t.repository.id = :arg0",
				Tree.Database.class);
		query.setParameter("arg0", repositoryId);

		try {
			List<Tree.Database> result = query.getResultList();
			em.close();
			return result;
		} catch (NoResultException e) {
			return null;
		}

	}

}
