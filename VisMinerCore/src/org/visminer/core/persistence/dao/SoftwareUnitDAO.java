package org.visminer.core.persistence.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.visminer.core.model.SoftwareUnit;
import org.visminer.core.persistence.Connection;

public class SoftwareUnitDAO {

	public SoftwareUnitDAO() {
	}
	
	public SoftwareUnit.Database save(SoftwareUnit.Database softwareEntty) {

		EntityManager em = Connection.getEntityManager();
		em.getTransaction().begin();
		SoftwareUnit.Database resp = em.merge(softwareEntty);
		em.getTransaction().commit();
		em.close();
		return resp;

	}

	public List<SoftwareUnit.Database> getByCommitAndFile(int commitId,
			int fileId) {

		EntityManager em = Connection.getEntityManager();
		TypedQuery<SoftwareUnit.Database> query = em
				.createQuery(
						"select s from SoftwareEntity s where s.fileXCommit.commit.id = :arg0 and s.fileXCommit.file.id = :arg1",
						SoftwareUnit.Database.class);
		query.setParameter("arg0", commitId);
		query.setParameter("arg1", fileId);

		try {
			List<SoftwareUnit.Database> resp = query.getResultList();
			em.close();
			return resp;
		} catch (NoResultException e) {
			em.close();
			return null;
		}

	}

}
