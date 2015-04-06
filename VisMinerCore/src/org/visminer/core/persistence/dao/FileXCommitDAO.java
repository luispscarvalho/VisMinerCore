package org.visminer.core.persistence.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.visminer.core.model.database.FileXCommit;
import org.visminer.core.model.database.FileXCommitPK;
import org.visminer.core.persistence.Connection;

public class FileXCommitDAO {

	public void saveAll(List<FileXCommit> filesStates){
		
		EntityManager em = Connection.getEntityManager();
		em.getTransaction().begin();
		
		for(FileXCommit fileState : filesStates){
			em.persist(fileState);
		}
		
		em.getTransaction().commit();
		em.close();
		
	}
	
	public FileXCommit get(FileXCommitPK id){
		
		EntityManager em = Connection.getEntityManager();
		try{
			FileXCommit result = em.find(FileXCommit.class, id);
			em.close();
			return result;
		}catch(NoResultException e){
			return null;
		}
		
	}
	
}
