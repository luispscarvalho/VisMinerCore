package org.visminer.core.persistence.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.visminer.core.model.database.FileDB;
import org.visminer.core.persistence.GenericDAO;

public class FileDAO extends GenericDAO<FileDB>{

	public Integer getIdByCode(String code){
		EntityManager em = conn.getEntityManager();
		Query query = em.createQuery("select f.id from File f where f.code = :code");
		query.setParameter("code", code);
		
		int result;
		
		try{
			result = (Integer) query.getSingleResult();
		}catch(Exception e){
			result = 0;
		}
		
		em.close();
		return result;
	}
	
}