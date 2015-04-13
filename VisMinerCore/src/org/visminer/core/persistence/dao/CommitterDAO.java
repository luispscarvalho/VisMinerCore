package org.visminer.core.persistence.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.visminer.core.model.database.CommitterDB;
import org.visminer.core.persistence.GenericDAO;

public class CommitterDAO extends GenericDAO<CommitterDB>{

	public CommitterDB getByEmail(String email){
		EntityManager em = conn.getEntityManager();
		TypedQuery<CommitterDB> query = em.createQuery("select c from Committer c where c.email = :email", CommitterDB.class);
		query.setParameter("email", email);
		
		CommitterDB result;
		try{
			result = query.getSingleResult();
		}catch(Exception e){
			result = null;
		}
		
		em.close();
		return result;
	}
	
}
