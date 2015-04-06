package org.visminer.core.persistence.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.visminer.core.model.database.SoftwareUnitDB;
import org.visminer.core.persistence.Connection;

public class SoftwareUnitDAO {

	public SoftwareUnitDAO() {
	}

	public SoftwareUnitDB save(SoftwareUnitDB softwareEntty) {

		EntityManager em = Connection.getEntityManager();
		em.getTransaction().begin();
		SoftwareUnitDB resp = em.merge(softwareEntty);
		em.getTransaction().commit();
		em.close();
		return resp;

	}

	public List<SoftwareUnitDB> getByCommitAndFile(int commitId, int fileId) {

		EntityManager em = Connection.getEntityManager();
		TypedQuery<SoftwareUnitDB> query = em
				.createQuery(
						"select s from SoftwareEntity s where s.fileXCommit.commit.id = :arg0 and s.fileXCommit.file.id = :arg1",
						SoftwareUnitDB.class);
		query.setParameter("arg0", commitId);
		query.setParameter("arg1", fileId);

		try {
			List<SoftwareUnitDB> resp = query.getResultList();
			em.close();
			return resp;
		} catch (NoResultException e) {
			em.close();
			return null;
		}

	}

}
