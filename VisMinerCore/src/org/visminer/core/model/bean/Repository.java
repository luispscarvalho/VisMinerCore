package org.visminer.core.model.bean;

import java.util.List;

import org.eclipse.persistence.internal.libraries.antlr.runtime.tree.Tree;

public class Repository {

	private int id;
	private String path;
	private String name;
	private String remoteOwner;
	private String remoteName;
	private int type;
	private int remoteType;

	private List<Committer> committers;
	private List<Commit> commits;
	private List<Tree> trees;
	private List<File> files;

	private Commit currentCommit;
	private Tree currentTree;

	public Repository() {
	}

	public Repository(int id, String path, String name, String remoteOwner,
			String remoteName, int type, int remoteType) {
		super();
		this.id = id;
		this.path = path;
		this.name = name;
		this.remoteOwner = remoteOwner;
		this.remoteName = remoteName;
		this.type = type;
		this.remoteType = remoteType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemoteOwner() {
		return remoteOwner;
	}

	public void setRemoteOwner(String remote_owner) {
		this.remoteOwner = remote_owner;
	}

	public String getRemoteName() {
		return remoteName;
	}

	public void setRemoteName(String remote_name) {
		this.remoteName = remote_name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getRemoteType() {
		return remoteType;
	}

	public void setRemoteType(int remoteType) {
		this.remoteType = remoteType;
	}

	public List<Committer> getCommitters() {
		return committers;
	}

	public List<Commit> getCommits() {
		return commits;
	}

	public List<Tree> getTrees() {
		return trees;
	}

	public List<File> getFiles() {
		return files;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}

	public Commit getCurrentCommit() {
		return currentCommit;
	}

	public void setCurrentCommit(Commit currentCommit) {
		this.currentCommit = currentCommit;
	}

	public Tree getCurrentTree() {
		return currentTree;
	}

	public void setCurrentTree(Tree currentTree) {
		this.currentTree = currentTree;
	}

}