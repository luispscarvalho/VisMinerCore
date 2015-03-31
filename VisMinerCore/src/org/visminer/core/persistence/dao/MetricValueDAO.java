package org.visminer.core.persistence.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.visminer.core.model.MetricValue;
import org.visminer.core.persistence.Connection;

public class MetricValueDAO {

	public void saveAll(List<MetricValue.Database> metricValues) {

		EntityManager em = Connection.getEntityManager();
		em.getTransaction().begin();
		for (int i = 0; i < metricValues.size(); i++) {
			em.merge(metricValues.get(i)).getId();
		}
		em.getTransaction().commit();
		em.close();

	}

	public List<MetricValue.Database> getBySoftwareEntity(int softwareEntityId) {

		EntityManager em = Connection.getEntityManager();
		TypedQuery<MetricValue.Database> query = em
				.createQuery(
						"select mv from MetricValue mv join mv.metric m where mv.softwareEntity.id = :arg0",
						MetricValue.Database.class);
		query.setParameter("arg0", softwareEntityId);

		try {
			List<MetricValue.Database> resp = query.getResultList();
			em.close();
			return resp;
		} catch (NoResultException e) {
			em.close();
			return null;
		}

	}

}
