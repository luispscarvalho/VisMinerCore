package org.visminer.core.persistence.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.visminer.core.model.database.MetricValueDB;
import org.visminer.core.persistence.Connection;

public class MetricValueDAO {

	public void saveAll(List<MetricValueDB.Database> metricValues) {

		EntityManager em = Connection.getEntityManager();
		em.getTransaction().begin();
		for (int i = 0; i < metricValues.size(); i++) {
			em.merge(metricValues.get(i)).getId();
		}
		em.getTransaction().commit();
		em.close();

	}

	public List<MetricValueDB.Database> getBySoftwareEntity(int softwareEntityId) {

		EntityManager em = Connection.getEntityManager();
		TypedQuery<MetricValueDB.Database> query = em
				.createQuery(
						"select mv from MetricValue mv join mv.metric m where mv.softwareEntity.id = :arg0",
						MetricValueDB.Database.class);
		query.setParameter("arg0", softwareEntityId);

		try {
			List<MetricValueDB.Database> resp = query.getResultList();
			em.close();
			return resp;
		} catch (NoResultException e) {
			em.close();
			return null;
		}

	}

}
