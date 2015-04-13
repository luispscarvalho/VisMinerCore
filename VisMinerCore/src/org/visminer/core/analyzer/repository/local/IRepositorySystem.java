package org.visminer.core.analyzer.repository.local;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.visminer.core.ast.AST;
import org.visminer.core.model.bean.Commit;
import org.visminer.core.model.bean.Committer;
import org.visminer.core.model.bean.File;
import org.visminer.core.model.bean.FileState;
import org.visminer.core.model.bean.Tree;


public interface IRepositorySystem {

	public void open(String repositoryPath);

	public String getAbsolutePath();

	public List<Committer> getCommitters();

	public List<Tree> getTrees();

	public List<String> getCommitsNames(String treeName);

	public Date getLastCommitDate(String treeName);

	public List<Commit> getCommits();

	public List<Commit> getCommits(Committer committer);
	
	public List<Commit> getCommits(String treeName);

	public AST getAST(String commitName, String filePath);

	public Map<File, FileState> getCommitedFiles(String commitName);

	public void close();
	
}