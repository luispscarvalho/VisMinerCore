package org.visminer.core.persistence;

import java.util.List;

import javax.persistence.EntityManager;

public class GenericDAO<T> {

	protected Database conn = Database.getInstance();
	
	public void batchSave(List<T> objects){
		
		EntityManager em = conn.getEntityManager();
		em.getTransaction().begin();
		
		for(int i = 0; i < objects.size(); i++){
			em.persist(objects.get(i));
			if((i % 1000) == 0){
				em.getTransaction().commit();
				em.clear();
				em.getTransaction().begin();
			}
		}
		
		em.getTransaction().commit();
		em.close();
		
	}
	
	public void batchRefresh(List<T> objects){
		
		EntityManager em = conn.getEntityManager();
		em.getTransaction().begin();
		
		for(int i = 0; i < objects.size(); i++){			
			em.merge(objects.get(i));
			if((i % 1000) == 0){
				em.getTransaction().commit();
				em.clear();
				em.getTransaction().begin();
			}
		}
		
		em.getTransaction().commit();
		em.close();
		
	}
	
	public void save(T object){
		
		EntityManager em = conn.getEntityManager();
		em.getTransaction().begin();
		em.persist(object);
		em.getTransaction().commit();
		em.close();
		
	}
	
	public void delete(T object){
		
		EntityManager em = conn.getEntityManager();
		em.getTransaction().begin();
		em.remove(object);
		em.getTransaction().commit();
		em.close();
		
	}
	
	public void update(T object){
		
		EntityManager em = conn.getEntityManager();
		em.getTransaction().begin();
		em.merge(object);
		em.getTransaction().commit();
		em.close();
		
	}	
	
}