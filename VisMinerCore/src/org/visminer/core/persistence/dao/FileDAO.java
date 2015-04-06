package org.visminer.core.persistence.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.visminer.core.model.database.FileDB;
import org.visminer.core.persistence.Connection;

public class FileDAO {

	public FileDB getBySha(String sha) {

		EntityManager em = Connection.getEntityManager();
		TypedQuery<FileDB> query = em.createQuery(
				"select f from File f where f.sha = :arg0", FileDB.class);
		query.setParameter("arg0", sha);

		try {
			FileDB result = query.getSingleResult();
			em.close();
			return result;
		} catch (NoResultException e) {
			return null;
		}

	}

	public FileDB save(FileDB file) {

		EntityManager em = Connection.getEntityManager();
		em.getTransaction().begin();
		FileDB f = em.merge(file);
		em.getTransaction().commit();
		em.close();
		return f;

	}

	public List<FileDB> getByCommit(int idCommit) {

		EntityManager em = Connection.getEntityManager();
		TypedQuery<FileDB> query = em
				.createQuery(
						"select f from File f join f.fileXCommits fxc where fxc.id.commitId = :arg0",
						FileDB.class);
		query.setParameter("arg0", idCommit);

		try {
			List<FileDB> files = query.getResultList();
			em.close();
			return files;
		} catch (NoResultException e) {
			return null;
		}

	}

}