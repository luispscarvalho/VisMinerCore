package org.visminer.core.analyzer;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.visminer.core.ast.AST;
import org.visminer.core.model.Commit;
import org.visminer.core.model.Committer;
import org.visminer.core.model.Tree;

public interface IRepositorySystem {

	public void open(String repositoryPath);

	public String getAbsolutePath();

	public Set<Committer.Business> getContributors();

	public List<Tree.Business> getTrees();

	public List<String> getCommitsNames(String treeName);

	public Date getLastCommitDate(String treeName);

	public List<Commit.Business> getCommits();

	public List<String> getFiles(String sha);

	public AST getAST(String commitName, String filePath);

	public Map<String, Boolean> getCommitedFiles(String commitName);

	public void close();

}