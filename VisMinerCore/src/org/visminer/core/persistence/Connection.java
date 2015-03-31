package org.visminer.core.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.visminer.core.config.DBConfig;

public class Connection {

	private static EntityManagerFactory managerFactory = null;	
	private final static String PERSISTENCE_UNIT = "VisMiner";
	
	public static void setDBConfig(DBConfig config){
		managerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT, config.toMap());
	}
	
	public static void close(){
		managerFactory.close();
	}
	
	public static boolean isOpen(){
		return managerFactory.isOpen();
	}

	public static EntityManager getEntityManager(){
		return managerFactory.createEntityManager();
	}	
	
}