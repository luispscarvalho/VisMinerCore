package org.visminer.core.persistence.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.visminer.core.model.database.MetricDB;
import org.visminer.core.persistence.GenericDAO;

public class MetricDAO extends GenericDAO<MetricDB> {

	public MetricDB getByName(String name){
		
		EntityManager em = conn.getEntityManager();
		TypedQuery<MetricDB> query = em.createQuery("select m from Metric m where m.name = :names", MetricDB.class);
		query.setParameter("name", name);
		
		List<MetricDB> result = query.getResultList();
		em.close();
		
		return result.get(0);
	}
	
}
