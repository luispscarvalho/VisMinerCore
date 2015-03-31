package org.visminer.core.analyzer;

import org.visminer.core.model.Repository;
import org.visminer.core.persistence.dao.RepositoryDAO;
import org.visminer.core.utility.StringUtils;

public class RepositoryUpdater {

	public static Repository.Database save(Repository.Business repository, IRepositorySystem repositorySystem){
		RepositoryDAO dao = new RepositoryDAO();
		
		repository.setPath(repositorySystem.getAbsolutePath());
		repository.setUid(StringUtils.sha1(repository.getPath()));
		
		Repository.Database bizRepository = dao.save(repository);
		
		return bizRepository;
	}
	
}
