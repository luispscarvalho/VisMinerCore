package org.visminer.core.persistence.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.visminer.core.model.database.CommitDB;
import org.visminer.core.persistence.GenericDAO;

public class CommitDAO extends GenericDAO<CommitDB>{

	public List<CommitDB> getByNames(List<String> names){
		if(names == null)
			return null;
		
		EntityManager em = conn.getEntityManager();
		TypedQuery<CommitDB> query = em.createQuery("select new org.visminer.model.database.Commit(c.id)"
				+ " from Commit c where c.code in :names", CommitDB.class);
		query.setParameter("names", names);
		
		List<CommitDB> result = query.getResultList();
		em.close();
		
		return result;
	}
	
}