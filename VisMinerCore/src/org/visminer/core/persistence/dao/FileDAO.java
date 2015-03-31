package org.visminer.core.persistence.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.visminer.core.model.File;
import org.visminer.core.persistence.Connection;

public class FileDAO {

	public File.Database getBySha(String sha) {

		EntityManager em = Connection.getEntityManager();
		TypedQuery<File.Database> query = em.createQuery(
				"select f from File f where f.sha = :arg0", File.Database.class);
		query.setParameter("arg0", sha);

		try {
			File.Database result = query.getSingleResult();
			em.close();
			return result;
		} catch (NoResultException e) {
			return null;
		}

	}

	public File.Database save(File.Database file) {

		EntityManager em = Connection.getEntityManager();
		em.getTransaction().begin();
		File.Database f = em.merge(file);
		em.getTransaction().commit();
		em.close();
		return f;

	}

	public List<File.Database> getByCommit(int idCommit) {

		EntityManager em = Connection.getEntityManager();
		TypedQuery<File.Database> query = em
				.createQuery(
						"select f from File f join f.fileXCommits fxc where fxc.id.commitId = :arg0",
						File.Database.class);
		query.setParameter("arg0", idCommit);

		try {
			List<File.Database> files = query.getResultList();
			em.close();
			return files;
		} catch (NoResultException e) {
			return null;
		}

	}

}