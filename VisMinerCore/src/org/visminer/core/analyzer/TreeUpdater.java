package org.visminer.core.analyzer;

import java.io.IOException;
import java.util.List;

import org.visminer.core.model.Repository;
import org.visminer.core.model.Tree;
import org.visminer.core.persistence.dao.CommitDAO;
import org.visminer.core.persistence.dao.TreeDAO;

public class TreeUpdater {

	public static void save(IRepositorySystem repositorySystem, Repository.Database repository) throws RevisionSyntaxException, MissingObjectException, IncorrectObjectTypeException, AmbiguousObjectException, GitAPIException, IOException{
		CommitDAO commitDAO = new CommitDAO();
		TreeDAO treeDAO = new TreeDAO();
		
		List<Tree.Business> trees = repositorySystem.getTrees();
		for(Tree.Business tree : trees){
			tree.setRepository(repository);
			List<String> names = repositorySystem.getCommitsNames(tree.getFullName());
			tree.setCommits(commitDAO.getByNames(names));			
		}
		
		treeDAO.batchSave(trees);
	}
	
}
